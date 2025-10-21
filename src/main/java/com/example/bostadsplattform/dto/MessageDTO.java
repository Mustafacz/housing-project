package com.example.bostadsplattform.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    private Long senderUserId;
    private Long receiverUserId;
    private Long listingId;
    private String content;
    private LocalDateTime createdAt;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSenderUserId() { return senderUserId; }
    public void setSenderUserId(Long senderUserId) { this.senderUserId = senderUserId; }

    public Long getReceiverUserId() { return receiverUserId; }
    public void setReceiverUserId(Long receiverUserId) { this.receiverUserId = receiverUserId; }

    public Long getListingId() { return listingId; }
    public void setListingId(Long listingId) { this.listingId = listingId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
