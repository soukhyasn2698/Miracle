package com.example.supplier_service.controller;

import java.util.*;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin("*")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier s) {
        Supplier supplier = new Supplier();
        supplier.setName(s.getName());
        supplier.setCode(s.getCode());
        supplier.setLocation(s.getLocation());
        supplier.setCategory(s.getCategory());
        supplier.setTier(s.getTier());
        supplier.setRiskScore(s.getRiskScore());
        supplier.setStatus(s.getStatus());
        supplier.setAlerts(s.getAlerts());

        return supplierService.addSupplier(supplier);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {
        Supplier updated = supplierService.updateSupplier(id, updates);
        return ResponseEntity.ok(updated);
    }
}


