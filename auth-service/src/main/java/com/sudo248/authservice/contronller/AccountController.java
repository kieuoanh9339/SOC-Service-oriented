package com.sudo248.authservice.contronller;

import com.sudo248.authservice.contronller.dto.ChangePasswordDto;
import com.sudo248.authservice.contronller.dto.SignInDto;
import com.sudo248.authservice.contronller.dto.SignUpDto;
import com.sudo248.authservice.contronller.dto.VerifyDto;
import com.sudo248.authservice.service.AccountService;
import com.sudo248.domain.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/auth")
@RestController
@Slf4j
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponse<?>> signIn(@Valid  @RequestBody SignInDto signInDto) {
//        return accountService.signIn(signInDto);

        return ResponseEntity.ok(new BaseResponse<>(200, "Sign in success"));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<?>> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        return accountService.signUp(signUpDto);
    }

    @PostMapping("/change-password")
    public ResponseEntity<BaseResponse<?>> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return null;
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<BaseResponse<?>> verifyOtp(@RequestBody VerifyDto verifyDto) {
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse<?>> handlerValidateException(MethodArgumentNotValidException ex)  {
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            error.put(fieldName, message);
        });
        log.error(ex.getMessage());
        return BaseResponse.status(HttpStatus.BAD_REQUEST, ex.getMessage(), error);
    }
}
