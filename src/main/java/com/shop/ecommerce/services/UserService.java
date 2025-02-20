package com.shop.ecommerce.services;

import com.shop.ecommerce.dtos.UserDTO;
import com.shop.ecommerce.dtos.builders.UserBuilder;
import com.shop.ecommerce.entities.User;
import com.shop.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Crează un utilizator
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        return UserBuilder.toUserDTO(user);
    }

    // Găsește un utilizator după id
    public Optional<UserDTO> getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserBuilder::toUserDTO);
    }

    // Găsește toți utilizatorii
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    // Actualizează un utilizator
    public Optional<UserDTO> updateUser(long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Actualizează datele utilizatorului
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setDateOfBirth(userDTO.getDateOfBirth());
            user.setAdmin(userDTO.isAdmin());
            // Salvează utilizatorul actualizat
            user = userRepository.save(user);
            return Optional.of(UserBuilder.toUserDTO(user));
        }
        return Optional.empty();
    }

    // Șterge un utilizator
    public boolean deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
