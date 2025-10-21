package com.example.bostadsplattform.dto;

public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phoneNumber;

    private String adress;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    //Setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


}
