package com.example.dashboard_service.entity;

public class HighestRiskSupplier {
	
	private String name;
    private int riskScore;
    private String category;

    public HighestRiskSupplier(String name, int riskScore, String category) {
        this.name = name;
        this.riskScore = riskScore;
        this.category = category;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(int riskScore) {
		this.riskScore = riskScore;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
