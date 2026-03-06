package com.example.supplier_service.service;

import org.springframework.stereotype.Service;
import com.example.supplier_service.entity.Alert;
import com.example.supplier_service.repository.AlertRepository;
import java.util.List;

@Service
public class AlertService {

    private final AlertRepository repository;

    public AlertService(AlertRepository repository) {
        this.repository = repository;
    }

    public List<Alert> getRecent3Alerts() {
        return repository.findRecent3Alerts();
    }
}
