package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.MessageDTO;
import com.example.bostadsplattform.model.Listing;
import com.example.bostadsplattform.model.Message;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.repository.ListingRepo;
import com.example.bostadsplattform.repository.MessageRepo;
import com.example.bostadsplattform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ListingRepo listingRepo;

    // Send a message
    public MessageDTO sendMessage(Long senderId, Long receiverId, Long listingId, String content) {
        User sender = userRepo.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepo.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Listing listing = null;
        if (listingId != null) {
            listing = listingRepo.findById(listingId)
                    .orElseThrow(() -> new RuntimeException("Listing not found"));
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setListing(listing);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());

        Message saved = messageRepo.save(message);
        return toDTO(saved);
    }

    // Get all messages for a user
    public List<MessageDTO> getMessagesForUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Message> messages = messageRepo.findBySenderOrReceiver(user, user);
        messages.sort(Comparator.comparing(Message::getCreatedAt));
        return messages.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Get all messages for a listing
    public List<MessageDTO> getMessagesForListing(Long listingId) {
        Listing listing = listingRepo.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        List<Message> messages = messageRepo.findByListing(listing);
        messages.sort(Comparator.comparing(Message::getCreatedAt));
        return messages.stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Get conversation between two users (both directions)
    public List<Message> getConversation(Long user1Id, Long user2Id) {
        User user1 = userRepo.findById(user1Id)
                .orElseThrow(() -> new RuntimeException("User 1 not found"));
        User user2 = userRepo.findById(user2Id)
                .orElseThrow(() -> new RuntimeException("User 2 not found"));

        return messageRepo.findBySenderAndReceiverOrReceiverAndSender(user1, user2, user1, user2);
    }


    // Convert entity to DTO
    private MessageDTO toDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setId(message.getId());
        dto.setSenderUserId(message.getSender().getId());
        dto.setReceiverUserId(message.getReceiver().getId());
        dto.setListingId(message.getListing() != null ? message.getListing().getId() : null);
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        return dto;
    }
}


