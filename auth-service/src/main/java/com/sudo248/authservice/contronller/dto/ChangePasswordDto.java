package com.sudo248.authservice.contronller.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ChangePasswordDto {
    @NotEmpty(message = "Password required")
    @Min(value = 8, message = "Password length must be 8 or higher")
    private String oldPassword;

    @NotEmpty(message = "Password required")
    @Min(value = 8, message = "Password length must be 8 or higher")
    private String newPassword;

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
