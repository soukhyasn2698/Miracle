package com.example.dashboard_service.entity;

import java.util.*;

public class DashboardResponse {

    private int totalSuppliers;
    private int activeRisks;
    private int avgRiskScore;
    private int alertsCount;
    private Map<String, Long> riskDistribution;
    private List<HighestRiskSupplier> highestRiskSuppliers;
    private List<PerformanceTrend> performanceTrends;
    private List<RecentAlert> recentAlerts;

    public DashboardResponse(int totalSuppliers,
                             int activeRisks,
                             int avgRiskScore,
                             int alertsCount,
                             Map<String, Long> riskDistribution,
                             List<HighestRiskSupplier> highestRiskSuppliers,
                             List<PerformanceTrend> performanceTrends,
                             List<RecentAlert> recentAlerts)  {
        this.totalSuppliers = totalSuppliers;
        this.activeRisks = activeRisks;
        this.avgRiskScore = avgRiskScore;
        this.alertsCount = alertsCount;
        this.riskDistribution = riskDistribution;
        this.highestRiskSuppliers = highestRiskSuppliers;
        this.performanceTrends = performanceTrends;
        this.recentAlerts = recentAlerts;
    }

	public int getTotalSuppliers() {
		return totalSuppliers;
	}

	public void setTotalSuppliers(int totalSuppliers) {
		this.totalSuppliers = totalSuppliers;
	}

	public int getActiveRisks() {
		return activeRisks;
	}

	public void setActiveRisks(int activeRisks) {
		this.activeRisks = activeRisks;
	}

	public int getAvgRiskScore() {
		return avgRiskScore;
	}

	public void setAvgRiskScore(int avgRiskScore) {
		this.avgRiskScore = avgRiskScore;
	}

	public int getAlertsCount() {
		return alertsCount;
	}

	public void setAlertsCount(int alertsCount) {
		this.alertsCount = alertsCount;
	}

	public Map<String, Long> getRiskDistribution() {
		return riskDistribution;
	}

	public void setRiskDistribution(Map<String, Long> riskDistribution) {
		this.riskDistribution = riskDistribution;
	}

	public List<HighestRiskSupplier> getHighestRiskSuppliers() {
		return highestRiskSuppliers;
	}

	public void setHighestRiskSuppliers(List<HighestRiskSupplier> highestRiskSuppliers) {
		this.highestRiskSuppliers = highestRiskSuppliers;
	}

	public List<PerformanceTrend> getPerformanceTrends() {
		return performanceTrends;
	}

	public void setPerformanceTrends(List<PerformanceTrend> performanceTrends) {
		this.performanceTrends = performanceTrends;
	}

	public List<RecentAlert> getRecentAlerts() {
		return recentAlerts;
	}

	public void setRecentAlerts(List<RecentAlert> recentAlerts) {
		this.recentAlerts = recentAlerts;
	}

	

    
}
