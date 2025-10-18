package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.SearchProfileDTO;
import com.example.bostadsplattform.model.SearchProfile;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.repository.SearchProfileRepo;
import com.example.bostadsplattform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchProfileService {

    @Autowired
    private SearchProfileRepo searchProfileRepo;

    @Autowired
    private UserRepo userRepo;

    public SearchProfileDTO createSearchProfile(SearchProfileDTO dto){

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        SearchProfile searchProfile = new SearchProfile();
        searchProfile.setPropertyType(dto.getPropertyType());
        searchProfile.setMinBudget(dto.getMinBudget());
        searchProfile.setMaxBudget(dto.getMaxBudget());
        searchProfile.setMinSize(dto.getMinSize());
        searchProfile.setMaxSize(dto.getMaxSize());
        searchProfile.setRooms(dto.getRooms());
        searchProfile.setArea(dto.getArea());
        searchProfile.setCreatedAt(dto.getCreatedAt());
        searchProfile.setUser(user);

        SearchProfile savedProfile = searchProfileRepo.save(searchProfile);

        SearchProfileDTO result = new SearchProfileDTO();
        result.setId(savedProfile.getId());
        result.setPropertyType(savedProfile.getPropertyType());
        result.setMinBudget(savedProfile.getMinBudget());
        result.setMaxBudget(savedProfile.getMaxBudget());
        result.setMinSize(savedProfile.getMinSize());
        result.setMaxSize(savedProfile.getMaxSize());
        result.setRooms(savedProfile.getRooms());
        result.setArea(savedProfile.getArea());
        result.setCreatedAt(savedProfile.getCreatedAt());
        result.setUserId(savedProfile.getUser().getId());

        return result;

    }

    public List<SearchProfileDTO> getAllProfiles() {
        return searchProfileRepo.findAll().stream().map(profile -> {
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
            return dto;
        }).toList();
    }

    public SearchProfileDTO getProfileById(Long id) {
        SearchProfile profile = searchProfileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Search profile not found"));

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

        return dto;
    }

    public List<SearchProfileDTO> getProfilesByUserId(Long userId) {
        List<SearchProfile> profiles = searchProfileRepo.findByUserId(userId);

        return profiles.stream().map(profile -> {
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
            return dto;
        }).toList();
    }

    public SearchProfileDTO updateSearchProfile(Long id, SearchProfileDTO dto) {
        SearchProfile profile = searchProfileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Search profile not found"));

        // Update fields
        profile.setPropertyType(dto.getPropertyType());
        profile.setMinBudget(dto.getMinBudget());
        profile.setMaxBudget(dto.getMaxBudget());
        profile.setMinSize(dto.getMinSize());
        profile.setMaxSize(dto.getMaxSize());
        profile.setRooms(dto.getRooms());
        profile.setArea(dto.getArea());
        profile.setCreatedAt(dto.getCreatedAt());

        // Save updated entity
        SearchProfile updatedProfile = searchProfileRepo.save(profile);

        // Convert back to DTO
        SearchProfileDTO result = new SearchProfileDTO();
        result.setId(updatedProfile.getId());
        result.setPropertyType(updatedProfile.getPropertyType());
        result.setMinBudget(updatedProfile.getMinBudget());
        result.setMaxBudget(updatedProfile.getMaxBudget());
        result.setMinSize(updatedProfile.getMinSize());
        result.setMaxSize(updatedProfile.getMaxSize());
        result.setRooms(updatedProfile.getRooms());
        result.setArea(updatedProfile.getArea());
        result.setCreatedAt(updatedProfile.getCreatedAt());
        result.setUserId(updatedProfile.getUser().getId());

        return result;
    }

    public void deleteSearchProfile(Long id) {
        SearchProfile profile = searchProfileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Search profile not found"));

        searchProfileRepo.delete(profile);
    }




}
