package com.example.performance_service.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.performance_service.entity.Performance;
import com.example.performance_service.repository.PerformanceRepository;

@Service
public class PerformanceService {

    private final PerformanceRepository repository;

    public PerformanceService(PerformanceRepository repository) {
        this.repository = repository;
    }

    public List<Performance> getAll() {
        return repository.findAll();
    }

    public Performance getById(int id) {
        return repository.findById(id).orElse(null);
    }
}