package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.LoginRegisterDTO;
import com.ynov.olympicker.dto.TokenDTO;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Create or get a token for a given user from a given center")
    @RequestMapping(value = "/access_token", method = {RequestMethod.POST})
    public TokenDTO getUserToken(@RequestBody LoginRegisterDTO loginInfos) {
        return authService.getUserToken(loginInfos.getEmail(), loginInfos.getPassword());
    }

    @Operation(summary = "Create a user")
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public TokenDTO createUser(@RequestBody LoginRegisterDTO createUserDTO) {
        return authService.createUser(createUserDTO);
    }

    @Operation(summary = "Get current user")
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me", method = {RequestMethod.GET})
    public User getUserByEmail(Principal principal) {
        return authService.whoami(principal);
    }
}
