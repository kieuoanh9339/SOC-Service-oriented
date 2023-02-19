package com.sudo248.authservice.service;

import com.sudo248.authservice.contronller.dto.ChangePasswordDto;
import com.sudo248.authservice.contronller.dto.SignInDto;
import com.sudo248.authservice.contronller.dto.SignUpDto;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.base.BaseService;
import org.springframework.http.ResponseEntity;

public interface AccountService extends BaseService {
    ResponseEntity<BaseResponse<?>> signIn(SignInDto signInDto);

    ResponseEntity<BaseResponse<?>> signUp(SignUpDto signUpDto);

    ResponseEntity<BaseResponse<?>> logOut(Long userId);

    ResponseEntity<BaseResponse<?>> changePassword(String userId, ChangePasswordDto changePasswordDto);
}
