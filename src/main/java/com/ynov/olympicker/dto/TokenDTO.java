package com.ynov.olympicker.dto;

import com.ynov.olympicker.entities.User;

public class TokenDTO {

    private String token;

    private User user;

    public TokenDTO(String token, User user) {
        this.setToken(token);
        this.setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
