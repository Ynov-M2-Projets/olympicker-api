package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.UpdateUserDTO;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }


    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean changePassword(String oldPassword, String newPassword, User user) {
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User updateUser(UpdateUserDTO userDTO, User user) {
        if (userDTO.getFirstname() != null) user.setFirstName(userDTO.getFirstname());
        if (userDTO.getLastname() != null) user.setLastName(userDTO.getLastname());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }
}
