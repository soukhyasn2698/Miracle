package com.example.supplier_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.supplier_service.entity.Supplier;


@Component
public class SupplierClient {

    private final RestTemplate restTemplate;

    public SupplierClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Supplier[] getSuppliers() {
        return restTemplate.getForObject(
                "http://localhost:8082/api/suppliers",
                Supplier[].class
        );
    }
}