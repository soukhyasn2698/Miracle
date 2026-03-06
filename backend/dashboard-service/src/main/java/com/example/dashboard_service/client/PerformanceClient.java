package com.example.dashboard_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.dashboard_service.entity.PerformanceTrend;

@Component
public class PerformanceClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String performanceServiceUrl = "http://localhost:8083/api/performance/trends";

    public PerformanceTrend[] getPerformanceTrends() {
        try {
            return restTemplate.getForObject(performanceServiceUrl, PerformanceTrend[].class);
        } catch (Exception e) {
            return new PerformanceTrend[0];
        }
    }
}
