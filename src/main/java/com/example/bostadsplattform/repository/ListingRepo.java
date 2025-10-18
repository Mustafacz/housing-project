package com.example.bostadsplattform.repository;



import com.example.bostadsplattform.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepo extends JpaRepository<Listing, Long> {
}
