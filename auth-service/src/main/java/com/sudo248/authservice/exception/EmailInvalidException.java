package com.sudo248.authservice.exception;

import com.sudo248.domain.common.ErrorMessage;
import com.sudo248.domain.exception.ApiException;
import org.springframework.http.HttpStatus;

public class EmailInvalidException extends ApiException {
    public EmailInvalidException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessage.EMAIL_INVALID);
    }
}
