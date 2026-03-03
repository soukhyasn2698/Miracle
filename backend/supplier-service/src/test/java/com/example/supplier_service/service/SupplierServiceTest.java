package com.example.supplier_service.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.supplier_service.client.SupplierClient;
import com.example.supplier_service.entity.Supplier;


public class SupplierServiceTest {
	
		
	    private SupplierServiceImpl supplierService;
	    
	    @BeforeEach
	    void setUp() {
	        supplierService = new SupplierServiceImpl();
	    }

	    @Test
	    void testGetAllSuppliers() {
	        List<Supplier> suppliers = supplierService.getAllSuppliers();

	        // Basic assertions
	        assertNotNull(suppliers, "Supplier list should not be null");
	        assertEquals(8, suppliers.size(), "There should be 8 mock suppliers");

	        // Optional: check first supplier's name
	        assertEquals("AutoParts Global Inc.", suppliers.get(0).getName());
	    }

}
