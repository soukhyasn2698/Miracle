package com.example.performance_service.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.example.performance_service.entity.Performance;
import com.example.performance_service.service.PerformanceService;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {
	
	private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping
    public List<Performance> getAll() {
        return performanceService.getAll();
    }

    @GetMapping("/{id}")
    public Performance getById(@PathVariable int id) {
        return performanceService.getById(id);
    }
}

