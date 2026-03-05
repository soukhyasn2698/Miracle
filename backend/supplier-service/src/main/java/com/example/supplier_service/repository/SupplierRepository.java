package com.example.supplier_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.supplier_service.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}