package com.example.dashboard_service.service;

import org.springframework.stereotype.Service;
import com.example.dashboard_service.client.SupplierClient;
import com.example.dashboard_service.client.PerformanceClient;
import com.example.dashboard_service.client.AlertClient;
import com.example.dashboard_service.entity.DashboardResponse;
import com.example.dashboard_service.entity.HighestRiskSupplier;
import com.example.dashboard_service.entity.SupplierData;
import com.example.dashboard_service.entity.PerformanceTrend;
import com.example.dashboard_service.entity.RecentAlert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DashboardService {

    private final SupplierClient supplierClient;
    private final PerformanceClient performanceClient;
    private final AlertClient alertClient;

    public DashboardService(SupplierClient supplierClient, PerformanceClient performanceClient, AlertClient alertClient) {
        this.supplierClient = supplierClient;
        this.performanceClient = performanceClient;
        this.alertClient = alertClient;
    }

    public DashboardResponse getDashboardMetrics() {

        SupplierData[] supplierArray = supplierClient.getSuppliers();
        List<SupplierData> suppliers = supplierArray != null
                ? Arrays.asList(supplierArray)
                : Collections.emptyList();

        int totalSuppliers = suppliers.size();

        int avgRiskScore = (int) Math.round(
                suppliers.stream()
                        .mapToInt(SupplierData::getRiskScore)
                        .average()
                        .orElse(0)
        );

        long activeRisks = suppliers.stream()
                .filter(s -> "active".equalsIgnoreCase(s.getStatus()))
                .filter(s -> s.getRiskScore() >= 60)
                .count();

        int alertsCount = suppliers.stream()
                .filter(s -> "active".equalsIgnoreCase(s.getStatus()))
                .mapToInt(SupplierData::getAlerts)
                .sum();

        Map<String, Long> riskDistribution = Map.of(
                "low", suppliers.stream().filter(s -> s.getRiskScore() < 30).count(),
                "medium", suppliers.stream().filter(s -> s.getRiskScore() >= 30 && s.getRiskScore() < 60).count(),
                "high", suppliers.stream().filter(s -> s.getRiskScore() >= 60 && s.getRiskScore() < 80).count(),
                "critical", suppliers.stream().filter(s -> s.getRiskScore() >= 80).count()
        );
        
        List<HighestRiskSupplier> highestRiskSuppliers = suppliers.stream()
                .filter(s -> s.getRiskScore() >= 60)
                .sorted(Comparator.comparingInt(SupplierData::getRiskScore).reversed())
                .map(s -> new HighestRiskSupplier(
                        s.getName(),
                        s.getRiskScore(),
                        s.getCategory()
                ))
                .collect(Collectors.toList());

        PerformanceTrend[] trendsArray = performanceClient.getPerformanceTrends();
        List<PerformanceTrend> performanceTrends = trendsArray != null
                ? Arrays.asList(trendsArray)
                : Collections.emptyList();

        RecentAlert[] alertsArray = alertClient.getRecentAlerts();
        List<RecentAlert> recentAlerts = alertsArray != null
                ? Arrays.asList(alertsArray)
                : Collections.emptyList();

        return new DashboardResponse(
                totalSuppliers,
                (int) activeRisks,
                avgRiskScore,
                alertsCount,
                riskDistribution,
                highestRiskSuppliers,
                performanceTrends,
                recentAlerts
        );
    }
}