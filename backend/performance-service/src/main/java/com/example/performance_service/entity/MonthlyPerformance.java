package com.example.performance_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "monthly_performance")
public class MonthlyPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String month;
    private int quality;
    private int delivery;
    private int overall;

    public MonthlyPerformance() {}

    public MonthlyPerformance(int id, String month, int quality, int delivery, int overall) {
        this.id = id;
        this.month = month;
        this.quality = quality;
        this.delivery = delivery;
        this.overall = overall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
