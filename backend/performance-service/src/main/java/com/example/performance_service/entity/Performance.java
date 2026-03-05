package com.example.performance_service.entity;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "supplier_id")
    private int supplierId;

    private int qualityScore;
    private int deliveryScore;
    private int costScore;
    private int complianceScore;
	public int getQualityScore() {
		return qualityScore;
	}
	public void setQualityScore(int qualityScore) {
		this.qualityScore = qualityScore;
	}
	public int getDeliveryScore() {
		return deliveryScore;
	}
	public void setDeliveryScore(int deliveryScore) {
		this.deliveryScore = deliveryScore;
	}
	public int getCostScore() {
		return costScore;
	}
	public void setCostScore(int costScore) {
		this.costScore = costScore;
	}
	public int getComplianceScore() {
		return complianceScore;
	}
	public void setComplianceScore(int complianceScore) {
		this.complianceScore = complianceScore;
	}
	public Performance(int id, int qualityScore, int deliveryScore, int costScore, int complianceScore) {
		super();
		this.id=id;
		this.qualityScore = qualityScore;
		this.deliveryScore = deliveryScore;
		this.costScore = costScore;
		this.complianceScore = complianceScore;
	}
	public Performance() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/*public static List<Performance> getMockPerformanceMetrics() {
        List<Performance> performanceMetrics = new ArrayList<>();
        
        performanceMetrics.add(new Performance(1,64,17,68,46));
        performanceMetrics.add(new Performance(2,87,42,91,89));
        performanceMetrics.add(new Performance(3,48,71,85,82));
        performanceMetrics.add(new Performance(4,98,95,32,96));
        performanceMetrics.add(new Performance(5,65,58,95,21));
        performanceMetrics.add(new Performance(6,22,89,78,94));
        performanceMetrics.add(new Performance(7,81,27,89,85));
        performanceMetrics.add(new Performance(8,96,94,45,96));
        
        return performanceMetrics;
        
	
	}*/
	

}
