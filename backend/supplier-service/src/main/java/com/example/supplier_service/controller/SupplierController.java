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
@CrossOrigin(origins = "*")
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
    public Supplier addSupplier(@RequestBody Supplier dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setCode(dto.getCode());
        supplier.setLocation(dto.getLocation());
        supplier.setCategory(dto.getCategory());
        supplier.setTier(dto.getTier());
        supplier.setRiskScore(dto.getRiskScore());
        supplier.setStatus(dto.getStatus());
        supplier.setAlerts(dto.getAlerts());

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


