package com.example.supplier_service.entity;

import java.util.*;

public class Supplier {

	
	private int id;
    private String name;
    private String code;
    private String location;
    private String category;
    private String tier;
    private int riskScore;
    private String status;
    private int alerts;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public int getRiskScore() {
		return riskScore;
	}
	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAlerts() {
		return alerts;
	}
	public void setAlerts(int alerts) {
		this.alerts = alerts;
	}
	public Supplier(int id, String name, String code, String location, String category, String tier, int riskScore,
			String status, int alerts) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.category = category;
		this.tier = tier;
		this.riskScore = riskScore;
		this.status = status;
		this.alerts = alerts;
	}
	public Supplier() {
		super();
	}
    
	public static List<Supplier> getMockSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        
        suppliers.add(new Supplier(1, "AutoParts Global Inc.", "APG-001", "Detroit, MI", 
                "Powertrain Components", "Tier 1", 25, "active", 0));
        
        suppliers.add(new Supplier(2, "PrecisionSteel Corp.", "PSC-003", "Cleveland, OH", 
                "Raw Materials", "Tier 2", 45, "active", 1));
        
        suppliers.add(new Supplier(3, "TechnoElectric Systems", "TES-005", "Irvine, CA", 
                "Electronics", "Tier 1", 52, "under-review", 5));
        
        suppliers.add(new Supplier(4, "SafetyFirst Components", "SFC-012", "Austin, TX", 
                "Safety Systems", "Tier 1", 18, "under-review", 0));
        
        suppliers.add(new Supplier(5, "Dragon Manufacturing Ltd.", "DMC-045", "Shenzhen, China", 
                "Interior Components", "Tier 2", 68, "under-review", 8));
        
        suppliers.add(new Supplier(6, "EuroPlastics GmbH", "EPG-009", "Stuttgart, Germany", 
                "Plastic Components", "Tier 1", 32, "active", 1));
        
        suppliers.add(new Supplier(7, "Midwest Castings LLC", "MCL-007", "Indianapolis, IN", 
                "Cast Components", "Tier 2", 58, "active", 3));
        
        suppliers.add(new Supplier(8, "NipponSeiki Automotive", "NSA-008", "Nagano, Japan", 
                "Instrumentation", "Tier 1", 22, "active", 0));
        
        return suppliers;
	}
}
