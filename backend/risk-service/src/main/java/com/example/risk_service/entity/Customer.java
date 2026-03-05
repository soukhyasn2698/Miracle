package com.example.risk_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "supplier_id")
	    private int supplierId;
	    
	    private String name;
	    private String location;
	    private String email;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getSupplierId() {
			return supplierId;
		}
		public void setSupplierId(int supplierId) {
			this.supplierId = supplierId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	    
}
