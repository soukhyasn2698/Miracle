package com.example.supplier_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;

    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Supplier updateSupplier(int id, Map<String, Object> updates) {

        Supplier supplier = repository.findById(id).orElse(null);
        if (supplier == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> supplier.setName((String) value);
                case "code" -> supplier.setCode((String) value);
                case "location" -> supplier.setLocation((String) value);
                case "category" -> supplier.setCategory((String) value);
                case "tier" -> supplier.setTier((String) value);
                case "riskScore" -> supplier.setRiskScore(((Number) value).intValue());
                case "status" -> supplier.setStatus((String) value);
                case "alerts" -> supplier.setAlerts(((Number) value).intValue());
            }
        });

        return repository.save(supplier);
    }
}