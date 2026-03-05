package com.example.risk_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.risk_service.entity.Customer;
import com.example.risk_service.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Customer getBySupplierId(int supplierId) {
        return repository.findBySupplierId(supplierId);
    }
}
