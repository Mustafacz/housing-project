package com.example.bostadsplattform.controller;

import com.example.bostadsplattform.dto.BrokerDTO;
import com.example.bostadsplattform.service.BrokerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brokers")
public class BrokerController {

    private final BrokerService brokerService;

    public BrokerController(BrokerService brokerService) {
        this.brokerService = brokerService;
    }

    @PostMapping
    public ResponseEntity<BrokerDTO> createBroker(@RequestBody BrokerDTO brokerDTO) {
        BrokerDTO created = brokerService.createBroker(brokerDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<BrokerDTO>> getAllBrokers() {
        return ResponseEntity.ok(brokerService.getAllBrokers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrokerDTO> getBrokerById(@PathVariable Long id) {
        return ResponseEntity.ok(brokerService.getBrokerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrokerDTO> updateBroker(@PathVariable Long id, @RequestBody BrokerDTO brokerDTO) {
        return ResponseEntity.ok(brokerService.updateBroker(id, brokerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBroker(@PathVariable Long id) {
        brokerService.deleteBroker(id);
        return ResponseEntity.noContent().build();
    }


}

