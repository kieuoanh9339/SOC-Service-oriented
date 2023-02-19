package com.sudo248.commonservice.controller;

import com.sudo248.commonservice.util.TokenUtils;
import com.sudo248.domain.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/token")
public class TokenController {

    private final TokenUtils tokenUtils;

    public TokenController(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @GetMapping("/generate/{userId}")
    public String generateToken(@PathVariable("userId") String userId) {
        return tokenUtils.generateToken(userId);
    }

    @GetMapping("/generate-refresh/{token}")
    public String generateRefreshToken(@PathVariable("token") String token) {
        return tokenUtils.generateRefreshToken(token);
    }

    @GetMapping("/user-id/{token}")
    public String getUserIdFromToken(@PathVariable("token") String token) {
        return tokenUtils.getUserIdFromToken(token);
    }

    @GetMapping("/validate/{token}")
    public Boolean validateToken(@PathVariable("token") String token) throws ApiException {
        return tokenUtils.validateToken(token);
    }
}
