package com.example.bostadsplattform.controller;

import com.example.bostadsplattform.dto.SearchProfileDTO;
import com.example.bostadsplattform.service.SearchProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchprofiles")
public class SearchProfileController {

    private final SearchProfileService searchProfileService;

    public SearchProfileController(SearchProfileService searchProfileService) {
        this.searchProfileService = searchProfileService;
    }

    @PostMapping
    public ResponseEntity<SearchProfileDTO> createProfile(@RequestBody SearchProfileDTO dto) {
        SearchProfileDTO created = searchProfileService.createSearchProfile(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<SearchProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(searchProfileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchProfileDTO> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(searchProfileService.getProfileById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SearchProfileDTO>> getProfilesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(searchProfileService.getProfilesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SearchProfileDTO> updateProfile(@PathVariable Long id, @RequestBody SearchProfileDTO dto) {
        return ResponseEntity.ok(searchProfileService.updateSearchProfile(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        searchProfileService.deleteSearchProfile(id);
        return ResponseEntity.noContent().build();
    }
}
