package com.example.supplier_service.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAll() {
        return supplierService.getSuppliers();
    }
    
    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }    
    
    @PatchMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {
        Supplier updated = supplierService.updateSupplier(id, updates);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}


