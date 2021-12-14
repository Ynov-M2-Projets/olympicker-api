package com.ynov.olympicker.services;

import com.ynov.olympicker.config.security.JwtTokenProvider;
import com.ynov.olympicker.dto.LoginRegisterDTO;
import com.ynov.olympicker.dto.TokenDTO;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User whoami(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }

    public User whoami(HttpServletRequest request) {
        return this.whoami(request.getUserPrincipal());
    }

    public TokenDTO getUserToken(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        String token = jwtTokenProvider.createToken(user);
        return new TokenDTO(token, user);
    }


    public TokenDTO createUser(LoginRegisterDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        String token = jwtTokenProvider.createToken(user);
        return new TokenDTO(token, user);
    }
}
