package com.sudo248.domain.exception;

import com.sudo248.domain.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiException extends Exception {
    private final HttpStatus status;

    public ApiException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
    }

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatusCode() {
        return status.value();
    }

    public ResponseEntity<BaseResponse<?>> getResponseEntity() {
        return getResponseEntity(null);
    }

    public <T> ResponseEntity<BaseResponse<?>> getResponseEntity(T body) {
        var response = new BaseResponse<>(
                status.value(),
                status.value() == 200,
                getMessage(),
                body
        );
        return ResponseEntity.status(status).body(response);
    }
}
