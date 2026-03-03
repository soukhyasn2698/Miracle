package com.example.supplier_service.service;

import java.util.List;

import org.springframework.stereotype.*;

import com.example.supplier_service.entity.Supplier;

@Service
public class SupplierService {
	
	private List<Supplier> s=Supplier.getMockSuppliers();
	
	 public List<Supplier> getSuppliers() {
	        return s; 
	    }

	public Supplier addSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		 //supplier.setId(s.size() + 1);
		 s.add(supplier);
		System.out.println("Added"+s);
		return null;
	}

}
