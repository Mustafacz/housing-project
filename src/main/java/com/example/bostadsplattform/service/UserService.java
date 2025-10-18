package com.example.bostadsplattform.service;

import com.example.bostadsplattform.dto.UserDTO;
import com.example.bostadsplattform.model.User;
import com.example.bostadsplattform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();

        //Set Values on user
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAdress(userDTO.getAdress());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepo.save(user);

        UserDTO result = new UserDTO();
        result.setFirstName(savedUser.getFirstName());
        result.setLastName(savedUser.getLastName());
        result.setEmail(savedUser.getEmail());
        result.setPhoneNumber(savedUser.getPhoneNumber());
        result.setAdress(savedUser.getAdress());
        result.setPassword(savedUser.getPassword());


        return result;
    }

    public List<UserDTO> getAllUsers(){

        return userRepo.findAll().stream().map(user -> {
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAdress(user.getAdress());
        return dto;
        }).toList();
    }


    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAdress(user.getAdress());
        return dto;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAdress(userDTO.getAdress());

        User updatedUser = userRepo.save(user);

        UserDTO result = new UserDTO();
        result.setFirstName(updatedUser.getFirstName());
        result.setLastName(updatedUser.getLastName());
        result.setEmail(updatedUser.getEmail());
        result.setPhoneNumber(updatedUser.getPhoneNumber());
        result.setAdress(updatedUser.getAdress());

        return result;
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

}
