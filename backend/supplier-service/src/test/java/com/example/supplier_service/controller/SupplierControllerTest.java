package com.example.supplier_service.controller;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private List<Supplier> mockSuppliers;

    public SupplierControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUp() {
        mockSuppliers = Supplier.getMockSuppliers();
    }

    @Test
    void testGetAllSuppliers() {
        when(supplierService.getAllSuppliers()).thenReturn(mockSuppliers);

        List<Supplier> result = supplierController.getAllSuppliers();

        assertNotNull(result);
        assertEquals(mockSuppliers.size(), result.size());
        assertEquals(mockSuppliers.get(0).getName(), result.get(0).getName());

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetSupplierByIdFound() {
        Supplier supplier = mockSuppliers.get(2);
        when(supplierService.getSupplierById(3)).thenReturn(supplier);

        Supplier result = supplierController.getSupplier(3); // if controller returns ResponseEntity
        // OR, if controller returns Supplier directly:
        // Supplier result = supplierController.getSupplier(3);

        assertNotNull(result);
        assertEquals("TechnoElectric Systems", result.getName());

        verify(supplierService, times(1)).getSupplierById(3);
    }

    @Test
    void testGetSupplierByIdNotFound() {
        when(supplierService.getSupplierById(99)).thenReturn(null);

        Supplier result = supplierController.getSupplier(99) != null
                ? supplierController.getSupplier(99)
                : null;

        assertNull(result);

        verify(supplierService, times(1)).getSupplierById(99);
    }
}