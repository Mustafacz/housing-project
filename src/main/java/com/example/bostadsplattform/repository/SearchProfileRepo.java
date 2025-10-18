package com.example.bostadsplattform.repository;



import com.example.bostadsplattform.model.SearchProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchProfileRepo extends JpaRepository<SearchProfile, Long> {
    List<SearchProfile> findByUserId(Long userId);

}

