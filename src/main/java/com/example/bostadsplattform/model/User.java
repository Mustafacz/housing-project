package com.example.bostadsplattform.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private String adress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SearchProfile> searchProfiles;

    // Role: "USER" or "BROKER" or "ADMIN"
    private String role = "USER";

    // Getters & Setters (add role getter/setter)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAdress() { return adress; }
    public void setAdress(String adress) { this.adress = adress; }

    public List<SearchProfile> getSearchProfiles() { return searchProfiles; }
    public void setSearchProfiles(List<SearchProfile> searchProfiles) { this.searchProfiles = searchProfiles; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
