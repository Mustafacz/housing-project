package com.example.bostadsplattform.controller;


import com.example.bostadsplattform.dto.SearchProfileDTO;
import com.example.bostadsplattform.model.SearchProfile;
import com.example.bostadsplattform.service.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/match/{listingId}")
    public ResponseEntity<List<SearchProfileDTO>> findMatches(@PathVariable Long listingId) {
        List<SearchProfileDTO> matches = matchingService.findMatchesForListing(listingId);
        return ResponseEntity.ok(matches);
    }
}


