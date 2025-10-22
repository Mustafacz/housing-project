package com.example.bostadsplattform.repository;

import com.example.bostadsplattform.model.Message;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    // Get all messages sent to or from a specific user
    List<Message> findBySenderOrReceiver(User sender, User receiver);

    // Get all messages related to a specific listing
    List<Message> findByListing(Listing listing);

    // Get conversation between two users (both directions)
    List<Message> findBySenderAndReceiverOrReceiverAndSender(
            User sender1, User receiver1,
            User sender2, User receiver2
    );

}
