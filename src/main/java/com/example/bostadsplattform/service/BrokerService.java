package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.BrokerDTO;
import com.example.bostadsplattform.model.Broker;
import com.example.bostadsplattform.repository.BrokerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {

    @Autowired
    private BrokerRepo brokerRepo;

    public BrokerDTO createBroker(BrokerDTO dto) {
        Broker broker = new Broker();
        broker.setFirstName(dto.getFirstName());
        broker.setLastName(dto.getLastName());
        broker.setEmail(dto.getEmail());
        broker.setPassword(dto.getPassword());
        broker.setPhoneNumber(dto.getPhoneNumber());
        broker.setCompanyName(dto.getCompanyName());
        broker.setSubscriptionActive(dto.isSubscriptionActive());

        Broker saved = brokerRepo.save(broker);

        dto.setSubscriptionActive(saved.isSubscriptionActive());
        return dto;
    }
}
