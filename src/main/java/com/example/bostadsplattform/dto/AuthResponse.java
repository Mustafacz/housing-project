package com.example.bostadsplattform.dto;

public class AuthResponse {

    private String token; // keep existing field if you still use it
    private String email;
    private String role;
    private String firstName;
    private String lastName;

    // Existing constructor
    // ✅ Constructor used for login/register (only token)
    public AuthResponse(String token) {
        this.token = token;
    }

    // ✅ Constructor used for whoAmI (user info only)
    public AuthResponse(String email, String role, String firstName, String lastName) {
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ✅ Constructor used when returning both token + user info
    public AuthResponse(String token, String email, String role, String firstName, String lastName) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
