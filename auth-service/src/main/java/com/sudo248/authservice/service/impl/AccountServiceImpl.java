package com.sudo248.authservice.service.impl;

import com.sudo248.authservice.contronller.dto.ChangePasswordDto;
import com.sudo248.authservice.contronller.dto.SignInDto;
import com.sudo248.authservice.contronller.dto.SignUpDto;
import com.sudo248.authservice.contronller.dto.TokenDto;
import com.sudo248.authservice.exception.EmailExistedException;
import com.sudo248.authservice.exception.EmailInvalidException;
import com.sudo248.authservice.exception.WrongPasswordException;
import com.sudo248.authservice.external.CommonService;
import com.sudo248.authservice.repository.AccountRepository;
import com.sudo248.authservice.repository.entity.Account;
import com.sudo248.authservice.service.AccountService;
import com.sudo248.authservice.service.model.AccountModel;
import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;
    private final CommonService commonService;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder encoder, ModelMapper mapper, CommonService commonService) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
        this.mapper = mapper;
        this.commonService = commonService;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> signIn(SignInDto signInDto) {
        return handleException(() -> {
            if (accountRepository.existsByEmail(signInDto.getEmailOrPhoneNumber())) {
                throw new EmailInvalidException();
            }
            AccountModel accountModel = mapper.map(accountRepository.getUserByEmail(signInDto.getEmailOrPhoneNumber()), AccountModel.class);
            if (!encoder.matches(signInDto.getPassword(), accountModel.getEmailOrPhoneNumber())) {
                throw new WrongPasswordException();
            }
            TokenDto token = new TokenDto(commonService.generateToken(accountModel.getUserId()));
            return BaseResponse.ok(token);
        });
    }

    @Override
    public ResponseEntity<BaseResponse<?>> signUp(SignUpDto signUpDto) {
        return handleException(() -> {
            var accountModel = signUpDto.toUserModel();
            log.info("accountModel: " + accountModel.toString());
            if (accountRepository.existsByEmail(accountModel.getEmailOrPhoneNumber())) {
                throw new EmailExistedException();
            }
            saveAccount(accountModel);
            TokenDto token = new TokenDto(commonService.generateToken(accountModel.getUserId()));
            log.info("token:" + token);
            return BaseResponse.ok(token);
        });
    }

    @Override
    public ResponseEntity<BaseResponse<?>> logOut(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> changePassword(String userId, ChangePasswordDto changePasswordDto) {
        return handleException(() -> {
            var accountModel = mapper.map(accountRepository.getReferenceById(userId), AccountModel.class);
            if (!encoder.matches(accountModel.getPassword(), changePasswordDto.getOldPassword())) {
                throw new WrongPasswordException();
            }
            accountModel.setPassword(changePasswordDto.getNewPassword());
            saveAccount(accountModel);
            return BaseResponse.ok("Change password success");
        });
    }

    private void saveAccount(AccountModel accountModel) throws ApiException {
        try {
            if (accountModel.getUserId() == null || accountModel.getUserId().isEmpty()) {
                int timestamp = LocalDateTime.now().getSecond();
                accountModel.setUserId(UUID.randomUUID() + "-" + timestamp);
            }
            String hashPassword = encoder.encode(accountModel.getPassword());
            accountModel.setPassword(hashPassword);
            accountRepository.save(mapper.map(accountModel, Account.class));
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INSUFFICIENT_STORAGE, e.getMessage());
        }
    }
}
