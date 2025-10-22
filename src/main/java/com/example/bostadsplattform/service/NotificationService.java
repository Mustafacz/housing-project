package com.example.bostadsplattform.service;

import com.example.bostadsplattform.model.Notification;
import com.example.bostadsplattform.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    // Create a new notification
    public Notification createNotification(Long userId, String type, String content) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setType(type);
        notification.setContent(content);
        return notificationRepo.save(notification);
    }

    // Get all notifications for a user
    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // Mark a notification as read
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return notificationRepo.save(notification);
    }
}
