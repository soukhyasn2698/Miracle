package com.example.dashboard_service.controller;

import org.springframework.web.bind.annotation.*;
import com.example.dashboard_service.service.DashboardService;
import com.example.dashboard_service.entity.DashboardResponse;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboardMetrics() {
        return dashboardService.getDashboardMetrics();
    }
}