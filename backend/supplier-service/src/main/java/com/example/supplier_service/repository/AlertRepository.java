package com.example.supplier_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.supplier_service.entity.Alert;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    
    @Query(value = "SELECT * FROM alerts ORDER BY date DESC LIMIT 3", nativeQuery = true)
    List<Alert> findRecent3Alerts();
}
