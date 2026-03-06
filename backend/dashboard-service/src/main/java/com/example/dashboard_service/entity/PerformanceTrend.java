package com.example.dashboard_service.entity;

public class PerformanceTrend {
    private String month;
    private int quality;
    private int delivery;
    private int overall;

    public PerformanceTrend(String month, int quality, int delivery, int overall) {
        this.month = month;
        this.quality = quality;
        this.delivery = delivery;
        this.overall = overall;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }
}
