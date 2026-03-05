package com.example.performance_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.performance_service.entity.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
}