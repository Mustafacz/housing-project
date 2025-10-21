package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.BrokerDTO;
import com.example.bostadsplattform.model.Broker;
import com.example.bostadsplattform.repository.BrokerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // GET all brokers
    public List<BrokerDTO> getAllBrokers() {
        return brokerRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET broker by ID
    public BrokerDTO getBrokerById(Long id) {
        Broker broker = brokerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Broker not found"));
        return mapToDTO(broker);
    }

    // UPDATE broker
    public BrokerDTO updateBroker(Long id, BrokerDTO dto) {
        Broker broker = brokerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Broker not found"));

        broker.setFirstName(dto.getFirstName());
        broker.setLastName(dto.getLastName());
        broker.setEmail(dto.getEmail());
        broker.setPhoneNumber(dto.getPhoneNumber());
        broker.setAdress(dto.getAdress());
        broker.setPassword(dto.getPassword());

        Broker updated = brokerRepo.save(broker);
        return mapToDTO(updated);
    }

    // DELETE broker
    public void deleteBroker(Long id) {
        if (!brokerRepo.existsById(id)) throw new RuntimeException("Broker not found");
        brokerRepo.deleteById(id);
    }

    // Helper method to map Broker -> BrokerDTO
    private BrokerDTO mapToDTO(Broker broker) {
        BrokerDTO dto = new BrokerDTO();
        dto.setId(broker.getId());
        dto.setFirstName(broker.getFirstName());
        dto.setLastName(broker.getLastName());
        dto.setEmail(broker.getEmail());
        dto.setPhoneNumber(broker.getPhoneNumber());
        dto.setAdress(broker.getAdress());
        dto.setPassword(broker.getPassword());
        return dto;
    }
}
