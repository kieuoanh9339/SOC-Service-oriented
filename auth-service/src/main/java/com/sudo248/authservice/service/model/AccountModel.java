package com.sudo248.authservice.service.model;

import com.sudo248.authservice.repository.entity.Provider;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountModel {
    private String userId;

    private String emailOrPhoneNumber;

    // hash by bcrypt
    private String password;

    private Provider provider;

    private boolean isValidate;

    private LocalDateTime createdAt;

    public AccountModel() {
    }

    public AccountModel(String userId) {
        this.userId = userId;
        this.isValidate = true;
    }

    public AccountModel(String userId, String emailOrPhoneNumber, String password, Provider provider, boolean isValidate, LocalDateTime createdAt) {
        this.userId = userId;
        this.emailOrPhoneNumber = emailOrPhoneNumber;
        this.password = password;
        this.provider = provider;
        this.isValidate = isValidate;
        this.createdAt = createdAt;
    }

    public AccountModel(String emailOrPhoneNumber, String password) {
        this(emailOrPhoneNumber, password, Provider.AUTH_SERVICE);
    }

    public AccountModel(String emailOrPhoneNumber, String password, Provider provider) {
        this(null, emailOrPhoneNumber, password, provider);
    }

    public AccountModel(String userId, String emailOrPhoneNumber, String password, Provider provider, LocalDateTime createdAt) {
        this(userId, emailOrPhoneNumber, password, provider, false, createdAt);
    }

    public AccountModel(String userId, String emailOrPhoneNumber, String password, Provider provider) {
        this(userId, emailOrPhoneNumber, password, provider, LocalDateTime.now());
    }

    public AccountModel(String userId, String emailOrPhoneNumber, String password, boolean isValidate) {
        this(userId, emailOrPhoneNumber, password, Provider.AUTH_SERVICE, isValidate, LocalDateTime.now());
    }

    public AccountModel(String userId, String emailOrPhoneNumber, String password) {
        this(userId, emailOrPhoneNumber, password, Provider.AUTH_SERVICE, false, LocalDateTime.now());
    }
}
