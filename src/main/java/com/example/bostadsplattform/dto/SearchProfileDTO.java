package com.example.bostadsplattform.dto;

import java.time.LocalDate;

public class SearchProfileDTO {
    private Long id;
    private String propertyType;
    private double minBudget;
    private double maxBudget;
    private double minSize;
    private double maxSize;
    private int rooms;
    private String area;
    private LocalDate createdAt;
    private Long userId; // reference to User

    private double matchScore; // add this to the DTO

    // Getters

    public Long getId() {
        return id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public double getMinBudget() {
        return minBudget;
    }

    public double getMaxBudget() {
        return maxBudget;
    }

    public double getMinSize() {
        return minSize;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public int getRooms() {
        return rooms;
    }

    public String getArea() {
        return area;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public double getMatchScore() { return matchScore; }


    // Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setMinBudget(double minBudget) {
        this.minBudget = minBudget;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public void setMinSize(double minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(double maxSize) {
        this.maxSize = maxSize;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }

}

