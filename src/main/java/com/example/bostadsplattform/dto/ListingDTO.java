package com.example.bostadsplattform.dto;

import com.example.bostadsplattform.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ListingDTO {

    private String title;
    private String description;
    private double price;
    private String location;
    private String imageUrl;
    private String createdAt;
    private Long brokerId; // reference to the broker

    // Getters

    public Long getBrokerId() { return brokerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getLocation() { return location; }
    public String getImageUrl() { return imageUrl; }
    public String getCreatedAt() { return createdAt; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setLocation(String location) { this.location = location; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public void setBrokerId(Long brokerId) { this.brokerId = brokerId; }
}
