package com.example.dashboard_service.controller;

import com.example.dashboard_service.entity.DashboardResponse;
import com.example.dashboard_service.service.DashboardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Mock
    private DashboardService dashboardService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    void shouldReturnDashboardResponse() {

        DashboardResponse mockResponse = new DashboardResponse(
                10, 2, 65, 4,
                Map.of("low", 1L, "medium", 2L, "high", 3L, "critical", 4L),
                null,
                null,
                null
        );

        when(dashboardService.getDashboardMetrics()).thenReturn(mockResponse);

        DashboardResponse response = dashboardController.getDashboardMetrics();

        // Assert
        assertNotNull(response);
        assertEquals(10, response.getTotalSuppliers());
        verify(dashboardService, times(1)).getDashboardMetrics();
    }
}