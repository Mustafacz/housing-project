package com.example.bostadsplattform.dto;

import com.example.bostadsplattform.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

public class ListingDTO {

    private String title;
    private String description;
    private double price;
    private String location;
    private List<String> imageUrls;
    private String createdAt;
    private String propertyType;
    private Long brokerId;
    private Double size;
    private int rooms;

    // Getters

    public Long getBrokerId() { return brokerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getLocation() { return location; }
    public List<String> getImageUrls() { return imageUrls; }
    public String getCreatedAt() { return createdAt; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setLocation(String location) { this.location = location; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
    public void setBrokerId(Long brokerId) { this.brokerId = brokerId; }
}
