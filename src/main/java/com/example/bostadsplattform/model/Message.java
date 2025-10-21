package com.example.bostadsplattform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to sender user
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Link to receiver user
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // Optional: link to a listing
    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    private String content;

    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public Listing getListing() { return listing; }
    public void setListing(Listing listing) { this.listing = listing; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
