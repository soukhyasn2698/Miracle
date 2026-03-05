package com.example.supplier_service.service;

import com.example.supplier_service.entity.Supplier;
import com.example.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceTest {

    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierService = new SupplierServiceImpl(supplierRepository);
    }

    @Test
    void testGetAllSuppliers() {
        Supplier s1 = new Supplier();
        s1.setId(1);
        s1.setName("AutoParts Global Inc.");

        Supplier s2 = new Supplier();
        s2.setId(2);
        s2.setName("PrecisionSteel Corp.");

        List<Supplier> mockSuppliers = List.of(s1, s2);

        when(supplierRepository.findAll()).thenReturn(mockSuppliers);

        List<Supplier> result = supplierService.getAllSuppliers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("AutoParts Global Inc.", result.get(0).getName());

        verify(supplierRepository, times(1)).findAll();
    }
}