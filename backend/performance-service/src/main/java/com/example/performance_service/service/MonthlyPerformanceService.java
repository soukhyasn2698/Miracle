package com.example.performance_service.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.performance_service.entity.MonthlyPerformance;
import com.example.performance_service.repository.MonthlyPerformanceRepository;

@Service
public class MonthlyPerformanceService {

    private final MonthlyPerformanceRepository repository;

    public MonthlyPerformanceService(MonthlyPerformanceRepository repository) {
        this.repository = repository;
    }

    public List<MonthlyPerformance> getLast4Months() {
        return repository.findLast4Months();
    }
}
