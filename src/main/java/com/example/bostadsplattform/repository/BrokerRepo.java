package com.example.bostadsplattform.repository;

import com.example.bostadsplattform.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrokerRepo extends JpaRepository<Broker, Long> {
    Optional<Broker> findByEmail(String email);
}

