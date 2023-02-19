package com.sudo248.authservice.contronller.dto;

import com.sudo248.authservice.repository.entity.Provider;
import com.sudo248.authservice.service.model.AccountModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignUpDto {
    private String userId;

    private String emailOrPhoneNumber;

    private String password;

    private Provider provider;

    private boolean isValidate;

    public SignUpDto() {
    }

    public SignUpDto(String emailOrPhoneNumber, String password) {
        this(null, emailOrPhoneNumber, password, Provider.AUTH_SERVICE);
    }

    public SignUpDto(String userId, String emailOrPhoneNumber, String password, Provider provider) {
        this(userId, emailOrPhoneNumber, password, provider, userId != null);
    }

    public SignUpDto(String userId, String emailOrPhoneNumber, String password, Provider provider, boolean isValidate) {
        this.userId = userId;
        this.emailOrPhoneNumber = emailOrPhoneNumber;
        this.password = password;
        this.provider = provider;
        this.isValidate = isValidate;
    }

    public AccountModel toUserModel() {
        if (provider == null) {
            provider = Provider.AUTH_SERVICE;
        }
        return new AccountModel(
                userId,
                emailOrPhoneNumber,
                password,
                provider,
                isValidate,
                LocalDateTime.now()
        );
    }
}
