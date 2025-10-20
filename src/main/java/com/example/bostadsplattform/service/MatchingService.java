package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.SearchProfileDTO;
import com.example.bostadsplattform.model.Listing;
import com.example.bostadsplattform.model.SearchProfile;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.repository.ListingRepo;
import com.example.bostadsplattform.repository.SearchProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.matches;

@Service
public class MatchingService {

    @Autowired
    private ListingRepo listingRepo;

    @Autowired
    private SearchProfileRepo searchProfileRepo;

    public List<SearchProfileDTO> findMatchesForListing(Long listingId) {
        Listing listing = listingRepo.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found"));

        List<SearchProfile> allProfiles = searchProfileRepo.findAll();

        // Filter using score threshold
        List<SearchProfile> matched = allProfiles.stream()
                .filter(profile -> calculateScore(listing, profile) >= 70) // threshold = 70
                .collect(Collectors.toList());

        // Convert to DTO with score
        return matched.stream().map(profile -> {
            SearchProfileDTO dto = new SearchProfileDTO();
            dto.setId(profile.getId());
            dto.setPropertyType(profile.getPropertyType());
            dto.setMinBudget(profile.getMinBudget());
            dto.setMaxBudget(profile.getMaxBudget());
            dto.setMinSize(profile.getMinSize());
            dto.setMaxSize(profile.getMaxSize());
            dto.setRooms(profile.getRooms());
            dto.setArea(profile.getArea());
            dto.setCreatedAt(profile.getCreatedAt());
            dto.setUserId(profile.getUser().getId());
            dto.setMatchScore(calculateScore(listing, profile)); // set the score
            return dto;
        }).collect(Collectors.toList());
    }

    private double calculateScore(Listing listing, SearchProfile profile) {
        double score = 0;

        // Property type
        if (profile.getPropertyType() != null && !profile.getPropertyType().isBlank()) {
            if (profile.getPropertyType().equalsIgnoreCase(listing.getPropertyType())) score += 30;
            else if (listing.getPropertyType().toLowerCase().contains(profile.getPropertyType().toLowerCase())) score += 15;
        }

        // Budget
        double price = listing.getPrice();
        if (price >= profile.getMinBudget() && price <= profile.getMaxBudget()) score += 25;
        else {
            double delta = Math.min(Math.abs(price - profile.getMinBudget()), Math.abs(price - profile.getMaxBudget()));
            if (delta / price < 0.1) score += 10; // within ±10%
        }

        // Size
        if (listing.getSize() != null && profile.getMinSize() > 0 && profile.getMaxSize() > 0) {
            if (listing.getSize() >= profile.getMinSize() && listing.getSize() <= profile.getMaxSize()) score += 20;
            else {
                double delta = Math.min(Math.abs(listing.getSize() - profile.getMinSize()), Math.abs(listing.getSize() - profile.getMaxSize()));
                if (delta / listing.getSize() < 0.15) score += 10; // ±15%
            }
        }

        // Rooms
        if (profile.getRooms() > 0) {
            int roomDiff = Math.abs(listing.getRooms() - profile.getRooms());
            if (roomDiff == 0) score += 15;
            else if (roomDiff == 1) score += 10; // ±1 room
        }

        // Area
        if (profile.getArea() != null && !profile.getArea().isBlank()) {
            String profileArea = profile.getArea().toLowerCase();
            String listingArea = listing.getLocation() == null ? "" : listing.getLocation().toLowerCase();
            if (listingArea.contains(profileArea) || profileArea.contains(listingArea)) score += 10;
        }

        return score;
    }

}
