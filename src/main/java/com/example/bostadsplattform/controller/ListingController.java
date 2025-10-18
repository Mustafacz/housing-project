package com.example.bostadsplattform.controller;

import com.example.bostadsplattform.dto.ListingDTO;
import com.example.bostadsplattform.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    // Create listing
    @PostMapping
    public ResponseEntity<ListingDTO> createListing(@RequestBody ListingDTO listingDTO) {
        return ResponseEntity.ok(listingService.createListing(listingDTO));
    }

    // Get all listings
    @GetMapping
    public ResponseEntity<List<ListingDTO>> getAllListings() {
        return ResponseEntity.ok(listingService.getAllListings());
    }

    // Get listing by ID
    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getListingById(@PathVariable Long id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    // Update listing
    @PutMapping("/{id}")
    public ResponseEntity<ListingDTO> updateListing(@PathVariable Long id, @RequestBody ListingDTO listingDTO) {
        return ResponseEntity.ok(listingService.updateListing(id, listingDTO));
    }

    // Delete listing
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}

