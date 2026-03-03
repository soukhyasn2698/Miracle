package com.example.performance_service.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.example.performance_service.entity.Performance;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {

    @GetMapping
    public List<Performance> getAll() {
        return Performance.getMockPerformanceMetrics();
    }

    @GetMapping("/{id}")
    public Performance getById(@PathVariable int id) {
        return Performance.getMockPerformanceMetrics().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

