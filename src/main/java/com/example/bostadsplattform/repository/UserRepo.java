package com.example.bostadsplattform.repository;

import com.example.bostadsplattform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
