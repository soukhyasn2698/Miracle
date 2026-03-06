package com.example.supplier_service.controller;

import org.springframework.web.bind.annotation.*;
import com.example.supplier_service.entity.Alert;
import com.example.supplier_service.service.AlertService;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping("/recent")
    public List<Alert> getRecent3Alerts() {
        return service.getRecent3Alerts();
    }
}
