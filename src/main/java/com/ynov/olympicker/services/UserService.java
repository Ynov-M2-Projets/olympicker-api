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
        System.out.println("Old password : " + oldPassword);
        System.out.println("New password : " + newPassword);

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User updateUser(UpdateUserDTO userDTO, User user) {
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }
}
