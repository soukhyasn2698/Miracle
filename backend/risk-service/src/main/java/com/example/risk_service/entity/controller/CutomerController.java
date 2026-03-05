package com.example.risk_service.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.risk_service.entity.Customer;
import com.example.risk_service.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CutomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<Customer> getCustomerBySupplierId(@PathVariable int supplierId) {
        Customer customer = customerService.getBySupplierId(supplierId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }
}
