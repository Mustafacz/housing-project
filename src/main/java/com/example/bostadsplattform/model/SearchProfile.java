package com.example.bostadsplattform.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class SearchProfile {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

    private String propertyType;

    private double minBudget;

    private double maxBudget;

    private double minSize;

    private double maxSize;

    private int rooms;

    private String area;

    private LocalDate createdAt;
    @ElementCollection
    @CollectionTable(name = "searchprofile_images", joinColumns = @JoinColumn(name = "searchprofile_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Getters

    public Long getId() {
        return Id;
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

    public User getUser() {
        return user;
    }

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }


    // Setters


    public void setId(Long id) {
        Id = id;
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

    public void setUser(User user) {
        this.user = user;
    }
}
