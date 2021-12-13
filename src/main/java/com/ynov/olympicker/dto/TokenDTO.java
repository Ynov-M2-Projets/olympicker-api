package com.ynov.olympicker.dto;

public class TokenDTO {

    private String token;

    public TokenDTO(String token) {
        this.setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
