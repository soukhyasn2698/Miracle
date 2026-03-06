package com.example.performance_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.performance_service.entity.MonthlyPerformance;
import java.util.List;

public interface MonthlyPerformanceRepository extends JpaRepository<MonthlyPerformance, Integer> {
    
    @Query(value = "SELECT * FROM monthly_performance ORDER BY id DESC LIMIT 4", nativeQuery = true)
    List<MonthlyPerformance> findLast4Months();
}
