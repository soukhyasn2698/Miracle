package com.example.dashboard_service.service;


import com.example.dashboard_service.client.SupplierClient;
import com.example.dashboard_service.entity.DashboardResponse;
import com.example.dashboard_service.entity.SupplierData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {

    @Mock
    private SupplierClient supplierClient;

    @InjectMocks
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateDashboardMetricsCorrectly() {

        // Arrange: create mock suppliers
        SupplierData s1 = new SupplierData(1, "A", "C1", "USA", "Cat1", "Tier1", 10, "active", 2);
        SupplierData s2 = new SupplierData(2, "B", "C2", "USA", "Cat2", "Tier1", 70, "active", 3);
        SupplierData s3 = new SupplierData(3, "C", "C3", "USA", "Cat3", "Tier2", 85, "inactive", 1);

        SupplierData[] mockSuppliers = { s1, s2, s3 };

        when(supplierClient.getSuppliers()).thenReturn(mockSuppliers);

        
        DashboardResponse response = dashboardService.getDashboardMetrics();

        
        assertEquals(3, response.getTotalSuppliers());
        assertEquals(1, response.getActiveRisks()); // active + risk>=60
        assertEquals(55.0, response.getAvgRiskScore());
        assertEquals(5, response.getAlertsCount()); // only active suppliers
        assertEquals(1, response.getRiskDistribution().get("low"));
        assertEquals(1, response.getRiskDistribution().get("high"));
        assertEquals(1, response.getRiskDistribution().get("critical"));

        verify(supplierClient, times(1)).getSuppliers();
    }
}

