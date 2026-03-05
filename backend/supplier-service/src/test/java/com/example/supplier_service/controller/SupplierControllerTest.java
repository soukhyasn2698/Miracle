package com.example.supplier_service.controller;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private List<Supplier> mockSuppliers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Supplier s1 = new Supplier();
        s1.setId(1);
        s1.setName("AutoParts Global Inc.");

        Supplier s2 = new Supplier();
        s2.setId(2);
        s2.setName("PrecisionSteel Corp.");

        Supplier s3 = new Supplier();
        s3.setId(3);
        s3.setName("TechnoElectric Systems");

        mockSuppliers = List.of(s1, s2, s3);
    }

    @Test
    void testGetAllSuppliers() {
        when(supplierService.getAllSuppliers()).thenReturn(mockSuppliers);

        List<Supplier> result = supplierController.getAllSuppliers();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("AutoParts Global Inc.", result.get(0).getName());

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetSupplierByIdFound() {
        when(supplierService.getSupplierById(3)).thenReturn(mockSuppliers.get(2));

        Supplier result = supplierController.getSupplier(3);

        assertNotNull(result);
        assertEquals("TechnoElectric Systems", result.getName());

        verify(supplierService, times(1)).getSupplierById(3);
    }

    @Test
    void testGetSupplierByIdNotFound() {
        when(supplierService.getSupplierById(99)).thenReturn(null);

        Supplier result = supplierController.getSupplier(99);

        assertNull(result);

        verify(supplierService, times(1)).getSupplierById(99);
    }
}