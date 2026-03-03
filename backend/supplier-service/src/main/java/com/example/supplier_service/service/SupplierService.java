package com.example.supplier_service.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.supplier_service.entity.Supplier;

@Service
public class SupplierService {
	
	
	private List<Supplier> suppliers;
	public SupplierService() {
	        this.suppliers = new ArrayList<>(Supplier.getMockSuppliers());
	  }
	public List<Supplier> getSuppliers() {
        return suppliers;
    }
	public Supplier addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		 //supplier.setId(s.size() + 1);
		suppliers.add(supplier);
		System.out.println("Added supplier= " + supplier.getName());
        System.out.println("Total suppliers= " + suppliers.size());
        return supplier;
	}
	
	public Supplier updateSupplier(int id, Map<String, Object> updates) {
        Supplier supplier = suppliers.stream()
                .filter(s -> s.getId()==(id))
                .findFirst()
                .orElse(null);

        if (supplier == null) {
            return null;
        }

        // Update only the fields that are provided
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    supplier.setName((String) value);
                    break;
                case "code":
                    supplier.setCode((String) value);
                    break;
                case "location":
                    supplier.setLocation((String) value);
                    break;
                case "category":
                    supplier.setCategory((String) value);
                    break;
                case "tier":
                    supplier.setTier((String) value);
                    break;
                case "riskScore":
                    supplier.setRiskScore(((Number) value).intValue());
                    break;
                case "status":
                    supplier.setStatus((String) value);
                    break;
                case "alerts":
                    supplier.setAlerts(((Number) value).intValue());
                    break;
            }
        });

        System.out.println("Updated supplier: " + supplier.getName());
        return supplier;
    }

}
