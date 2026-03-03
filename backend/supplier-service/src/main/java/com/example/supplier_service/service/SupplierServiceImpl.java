package com.example.supplier_service.service;


import java.util.*;
import org.springframework.stereotype.Service;

import com.example.supplier_service.entity.Supplier;


@Service
public class SupplierServiceImpl implements SupplierService {

    private final List<Supplier> suppliers = new ArrayList<>(Supplier.getMockSuppliers());

    @Override
    public List<Supplier> getAllSuppliers() {
        return Collections.unmodifiableList(suppliers);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        supplier.setId(suppliers.size() + 1); // auto increment
        suppliers.add(supplier);
        return supplier;
    }

    @Override
    public Supplier updateSupplier(int id, Map<String, Object> updates) {
    	Supplier supplier = suppliers.stream()
    	        .filter(s -> s.getId() == id)
    	        .findFirst()
    	        .orElse(null);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name": supplier.setName((String) value); break;
                case "code": supplier.setCode((String) value); break;
                case "location": supplier.setLocation((String) value); break;
                case "category": supplier.setCategory((String) value); break;
                case "tier": supplier.setTier((String) value); break;
                case "riskScore": supplier.setRiskScore(((Number) value).intValue()); break;
                case "status": supplier.setStatus((String) value); break;
                case "alerts": supplier.setAlerts(((Number) value).intValue()); break;
            }
        });

        return supplier;
    }

    @Override
    public Supplier getSupplierById(int id) {
        return suppliers.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
