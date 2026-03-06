package com.example.performance_service.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.performance_service.entity.MonthlyPerformance;
import com.example.performance_service.service.MonthlyPerformanceService;

@RestController
@RequestMapping("/api/performance/trends")
@CrossOrigin(origins = "*")
public class MonthlyPerformanceController {

    private final MonthlyPerformanceService service;

    public MonthlyPerformanceController(MonthlyPerformanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<MonthlyPerformance> getLast4Months() {
        return service.getLast4Months();
    }
}
