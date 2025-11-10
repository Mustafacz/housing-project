package com.example.bostadsplattform.model;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.List;


@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String description;
    private double price;
    private String location;
    private String createdAt;
    private String propertyType;
    private Double size;
    private int rooms;

    @ElementCollection
    @CollectionTable(name = "listing_images", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;



    //Getters

    public Long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getImageUrls() { return imageUrls; }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPropertyType() { return propertyType; }

    public Double getSize() { return size; }

    public int getRooms() { return rooms; }

    public Broker getBroker() { return broker; }

    //Setters

    public void setId(Long id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setRooms(int rooms) { this.rooms = rooms; }

    public void setSize(Double size) { this.size = size; }

    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public void setBroker(Broker broker) { this.broker = broker; }

}
