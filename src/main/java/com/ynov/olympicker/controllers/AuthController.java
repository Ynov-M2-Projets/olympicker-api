package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateUserDTO;
import com.ynov.olympicker.dto.LoginInfosDTO;
import com.ynov.olympicker.dto.TokenDTO;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Create or get a token for a given user from a given center")
    @RequestMapping(value = "/access_token", method = {RequestMethod.POST})
    public TokenDTO getUserToken(@RequestBody LoginInfosDTO loginInfos) {
        return authService.getUserToken(loginInfos.getEmail(), loginInfos.getPassword());
    }

    @Operation(summary = "Create a user")
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public TokenDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = authService.createUser(createUserDTO);
        return authService.getUserToken(user.getEmail(), user.getPassword());
    }
}