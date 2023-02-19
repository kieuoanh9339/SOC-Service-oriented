package com.sudo248.authservice.contronller.dto;

import lombok.Data;

@Data
public class TokenDto {
    private final String token;
    private final String refreshToken;

    public TokenDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public TokenDto(String token) {
        this.token = token;
        this.refreshToken = null;
    }
}
