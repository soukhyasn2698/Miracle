package com.example.dashboard_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.dashboard_service.entity.RecentAlert;

@Component
public class AlertClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String alertServiceUrl = "http://localhost:8082/api/suppliers/alerts/recent";

    public RecentAlert[] getRecentAlerts() {
        try {
            return restTemplate.getForObject(alertServiceUrl, RecentAlert[].class);
        } catch (Exception e) {
            return new RecentAlert[0];
        }
    }
}
