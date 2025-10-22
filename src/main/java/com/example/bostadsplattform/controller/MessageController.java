package com.example.bostadsplattform.controller;

import com.example.bostadsplattform.dto.MessageDTO;
import com.example.bostadsplattform.model.Message;
import com.example.bostadsplattform.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Send a message
    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam(required = false) Long listingId,
            @RequestParam String content) {

        MessageDTO dto = messageService.sendMessage(senderId, receiverId, listingId, content);
        return ResponseEntity.ok(dto);
    }

    // Get all messages for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessageDTO>> getMessagesForUser(@PathVariable Long userId) {
        List<MessageDTO> messages = messageService.getMessagesForUser(userId);
        return ResponseEntity.ok(messages);
    }

    // Get all messages for a listing
    @GetMapping("/listing/{listingId}")
    public ResponseEntity<List<MessageDTO>> getMessagesForListing(@PathVariable Long listingId) {
        List<MessageDTO> messages = messageService.getMessagesForListing(listingId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversation/{user1Id}/{user2Id}")
    public ResponseEntity<List<MessageDTO>> getConversation(
            @PathVariable Long user1Id,
            @PathVariable Long user2Id) {

        List<Message> messages = messageService.getConversation(user1Id, user2Id);

        // Convert to DTOs
        List<MessageDTO> dtos = messages.stream().map(msg -> {
            MessageDTO dto = new MessageDTO();
            dto.setId(msg.getId());
            dto.setSenderUserId(msg.getSender().getId());
            dto.setReceiverUserId(msg.getReceiver().getId());
            dto.setListingId(msg.getListing() != null ? msg.getListing().getId() : null);
            dto.setContent(msg.getContent());
            dto.setCreatedAt(msg.getCreatedAt());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtos);
    }
}
