package com.example.risk_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.risk_service.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findBySupplierId(int supplierId);
}
