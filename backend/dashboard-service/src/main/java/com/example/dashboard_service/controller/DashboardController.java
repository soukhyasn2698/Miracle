package com.example.dashboard_service.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.dashboard_service.entity.SupplierData;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
	private final RestTemplate restTemplate;
	
	public DashboardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	 @GetMapping
	    public Map<String, Object> getDashboardMetrics() {
	        // Call supplier-service to get supplier list
	        SupplierData[] suppliers = restTemplate.getForObject(
	            "http://localhost:8082/api/suppliers",
	            SupplierData[].class
	        );
	        
	     // Total Suppliers
	        
	        int totalSuppliers = suppliers != null ? suppliers.length : 0;
	     // Average Risk score
	        int avgRiskScore = 0;
	        if (suppliers != null && suppliers.length > 0) {
	            int totalRiskScore = 0;
	            for (SupplierData supplier : suppliers) {
	                totalRiskScore += supplier.getRiskScore();
	            }
	            avgRiskScore = totalRiskScore / suppliers.length;
	        }
	        //Active risks
	        int activeRisks = 0;
	        if (suppliers != null && suppliers.length > 0) {
	        	int totalRisks=0;
	        	for(SupplierData supplier:suppliers) {
	        		if(supplier.getStatus().equalsIgnoreCase("active"))totalRisks+=1;
	        	}
	        	activeRisks=totalRisks;
	        }
	        
	        //alerts count
	        int alertsCount = 0;
	        if (suppliers != null && suppliers.length > 0) {
	        	int totalAlerts=0;
	        	for(SupplierData supplier:suppliers) {
	        		if(supplier.getStatus().equalsIgnoreCase("active"))totalAlerts+=supplier.getAlerts();
	        	}
	        	alertsCount=totalAlerts;
	        }

	        return Map.of(
	            "totalSuppliers", totalSuppliers,
	            "activeRisks", activeRisks,
	            "avgRiskScore", avgRiskScore,
	            "alertsCount", alertsCount
	        );
	 }
}
