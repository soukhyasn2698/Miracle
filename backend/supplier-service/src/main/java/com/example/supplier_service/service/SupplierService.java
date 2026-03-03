package com.example.supplier_service.service;

import java.util.*;

import com.example.supplier_service.entity.Supplier;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Supplier addSupplier(Supplier supplier);
    Supplier updateSupplier(int id, Map<String, Object> updates);
    Supplier getSupplierById(int id);
}