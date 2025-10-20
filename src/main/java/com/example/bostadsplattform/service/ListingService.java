package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.ListingDTO;
import com.example.bostadsplattform.model.Broker;
import com.example.bostadsplattform.model.Listing;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.repository.BrokerRepo;
import com.example.bostadsplattform.repository.ListingRepo;
import com.example.bostadsplattform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingService {

    @Autowired
    private ListingRepo listingRepo;
    @Autowired
    private BrokerRepo brokerRepo;
    @Autowired
    private UserRepo userRepo;

    // Create a new listing
    public ListingDTO createListing(ListingDTO listingDTO) {
        Listing listing = new Listing();
        listing.setTitle(listingDTO.getTitle());
        listing.setDescription(listingDTO.getDescription());
        listing.setPrice(listingDTO.getPrice());
        listing.setLocation(listingDTO.getLocation());
        listing.setImageUrl(listingDTO.getImageUrl());
        listing.setCreatedAt(listingDTO.getCreatedAt());

        // Attach the broker
        Broker broker = brokerRepo.findById(listingDTO.getBrokerId())
                .orElseThrow(() -> new RuntimeException("Broker not found"));
        listing.setBroker(broker);


        Listing savedListing = listingRepo.save(listing);

        // Convert back to DTO
        ListingDTO result = new ListingDTO();
        result.setTitle(savedListing.getTitle());
        result.setDescription(savedListing.getDescription());
        result.setPrice(savedListing.getPrice());
        result.setLocation(savedListing.getLocation());
        result.setImageUrl(savedListing.getImageUrl());
        result.setCreatedAt(savedListing.getCreatedAt());
        result.setBrokerId(savedListing.getBroker().getId());


        return result;
    }

    // Get all listings
    public List<ListingDTO> getAllListings() {
        return listingRepo.findAll().stream().map(listing -> {
            ListingDTO dto = new ListingDTO();
            dto.setTitle(listing.getTitle());
            dto.setDescription(listing.getDescription());
            dto.setPrice(listing.getPrice());
            dto.setLocation(listing.getLocation());
            dto.setImageUrl(listing.getImageUrl());
            dto.setCreatedAt(listing.getCreatedAt());
            dto.setBrokerId(listing.getBroker().getId());

            return dto;
        }).collect(Collectors.toList());
    }

    // Get listing by ID
    public ListingDTO getListingById(Long id) {
        Listing listing = listingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));
        ListingDTO dto = new ListingDTO();
        dto.setTitle(listing.getTitle());
        dto.setDescription(listing.getDescription());
        dto.setPrice(listing.getPrice());
        dto.setLocation(listing.getLocation());
        dto.setImageUrl(listing.getImageUrl());
        dto.setCreatedAt(listing.getCreatedAt());
        dto.setBrokerId(listing.getBroker().getId());

        return dto;
    }

    // Update a listing
    public ListingDTO updateListing(Long id, ListingDTO listingDTO) {
        Listing listing = listingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        listing.setTitle(listingDTO.getTitle());
        listing.setDescription(listingDTO.getDescription());
        listing.setPrice(listingDTO.getPrice());
        listing.setLocation(listingDTO.getLocation());
        listing.setImageUrl(listingDTO.getImageUrl());
        listing.setCreatedAt(listingDTO.getCreatedAt());

        Listing saved = listingRepo.save(listing);

        ListingDTO dto = new ListingDTO();
        dto.setTitle(saved.getTitle());
        dto.setDescription(saved.getDescription());
        dto.setPrice(saved.getPrice());
        dto.setLocation(saved.getLocation());
        dto.setImageUrl(saved.getImageUrl());
        dto.setCreatedAt(saved.getCreatedAt());
        dto.setBrokerId(listing.getBroker().getId());

        return dto;
    }

    // Delete a listing
    public void deleteListing(Long id) {
        listingRepo.deleteById(id);
    }
}

