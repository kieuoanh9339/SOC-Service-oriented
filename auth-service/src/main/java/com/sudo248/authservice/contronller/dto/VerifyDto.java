package com.sudo248.authservice.contronller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VerifyDto {
    @NotEmpty(message = "Required email or phone number")
    private String emailOrPhoneNumber;

    @NotEmpty(message = "Required otp to verify")
    private String otp;
}
