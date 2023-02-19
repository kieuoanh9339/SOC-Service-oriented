package com.sudo248.authservice.exception;

import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends ApiException {
    public WrongPasswordException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.WRONG_PASSWORD);
    }
}
