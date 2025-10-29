package com.example.bostadsplattform.controller;

import com.example.bostadsplattform.dto.*;
import com.example.bostadsplattform.model.*;
import com.example.bostadsplattform.security.JwtUtil;
import com.example.bostadsplattform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // Register normal user
    @PostMapping("/register/user")
    public AuthResponse registerUser(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAdress(request.getAdress());
        user.setRole("USER");

        userService.createUserFromEntity(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }

    // Register broker
    @PostMapping("/register/broker")
    public AuthResponse registerBroker(@RequestBody BrokerRegisterRequest request) {
        Broker broker = new Broker();
        broker.setFirstName(request.getFirstName());
        broker.setLastName(request.getLastName());
        broker.setEmail(request.getEmail());
        broker.setPassword(passwordEncoder.encode(request.getPassword()));
        broker.setPhoneNumber(request.getPhoneNumber());
        broker.setAdress(request.getAdress());
        broker.setCompanyName(request.getCompanyName());
        broker.setSubscriptionActive(request.isSubscriptionActive());
        broker.setRole("BROKER");

        brokerService.createBrokerFromEntity(broker);

        String token = jwtUtil.generateToken(broker.getEmail(), broker.getRole());
        return new AuthResponse(token);
    }

    // Login (shared)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Fetch user or broker
        User user = userService.getUserByEmail(request.getEmail());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token);
    }

    @GetMapping("/whoami")
    public AuthResponse whoAmI(Authentication authentication) {
        String email = authentication.getName();

        try {
            Broker broker = brokerService.getBrokerByEmail(email);
            return new AuthResponse(broker.getEmail(), broker.getRole(), broker.getFirstName(), broker.getLastName());
        } catch (Exception e) {
            User user = userService.getUserByEmail(email);
            return new AuthResponse(user.getEmail(), user.getRole(), user.getFirstName(), user.getLastName());
        }
    }


}



