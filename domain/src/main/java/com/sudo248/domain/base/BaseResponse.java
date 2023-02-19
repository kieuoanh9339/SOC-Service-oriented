package com.sudo248.domain.base;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class BaseResponse<D> {
    private int statusCode;
    private boolean success;
    private String message;

    private D data;

    public BaseResponse(int statusCode, boolean success, String message, D data) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int statusCode, String message, D data) {
        this.statusCode = statusCode;
        this.success = (statusCode == 200) || (statusCode == 201);
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public BaseResponse(int statusCode, boolean success, String message) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }

    public BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.success = (statusCode == 200) || (statusCode == 201);
        this.message = message;
    }

    public BaseResponse(int statusCode, boolean success, D data) {
        this.statusCode = statusCode;
        this.success = success;
        this.data = data;
    }

    public static <Data> ResponseEntity<BaseResponse<?>> ok(Data body) {
        var response = new BaseResponse<>(
                200,
                "Success",
                body
        );
        return ResponseEntity.ok(response);
    }

    public static <Data> ResponseEntity<BaseResponse<?>> status(HttpStatus status, Data body) {
        var response = new BaseResponse<>(
                status.value(),
                status.getReasonPhrase(),
                body
        );
        return ResponseEntity.status(status).body(response);
    }

    public static <Data> ResponseEntity<BaseResponse<?>> status(HttpStatus status, String message, Data body) {
        var response = new BaseResponse<>(
                status.value(),
                message,
                body
        );
        return ResponseEntity.status(status).body(response);
    }
}
