package com.example.dashboard_service.entity;

import java.time.LocalDateTime;


public class RecentAlert {
    private String message;
    private String supplier;
    private LocalDateTime date;
    private String severity;

    public RecentAlert(String message, String supplier, LocalDateTime date, String severity) {
        this.message = message;
        this.supplier = supplier;
        this.date = date;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
