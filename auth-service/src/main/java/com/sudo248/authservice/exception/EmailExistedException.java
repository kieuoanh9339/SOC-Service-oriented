package com.sudo248.authservice.exception;

import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class EmailExistedException extends ApiException {

    public EmailExistedException() {
        super(HttpStatus.BAD_REQUEST, "Email existed");
    }
}
