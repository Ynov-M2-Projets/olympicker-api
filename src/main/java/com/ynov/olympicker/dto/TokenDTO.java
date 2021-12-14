package com.ynov.olympicker.dto;

import com.ynov.olympicker.entities.User;
import lombok.Data;

@Data
public class TokenDTO {

    private String token;

    private User user;

    public TokenDTO(String token, User user) {
        this.setToken(token);
        this.setUser(user);
    }
}
