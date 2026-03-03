package com.example.dashboard_service.client;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.dashboard_service.entity.SupplierData;

@Component
public class SupplierClient {

    private final RestTemplate restTemplate;

    public SupplierClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SupplierData[] getSuppliers() {
        return restTemplate.getForObject(
                "http://localhost:8082/api/suppliers",
                SupplierData[].class
        );
    }
}
