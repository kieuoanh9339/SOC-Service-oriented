package com.sudo248.authservice.contronller.dto;

import com.sudo248.authservice.service.model.AccountModel;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class SignInDto {

    private String emailOrPhoneNumber;

    @NotEmpty(message = "Password required")
    @Min(value = 8, message = "Password length must be 8 or higher")
    private String password;

    public SignInDto() {
    }

    public AccountModel toUserModel() {
        return new AccountModel(emailOrPhoneNumber, password);
    }
}
