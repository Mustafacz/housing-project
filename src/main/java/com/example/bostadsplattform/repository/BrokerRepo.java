package com.example.bostadsplattform.repository;

import com.example.bostadsplattform.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepo extends JpaRepository<Broker, Long> {
}

