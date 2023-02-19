package com.sudo248.domain.common;

public class ErrorMessage {
    public static final String EMAIL_INVALID = "Email invalid";
    public static final String WRONG_PASSWORD = "Wrong password";
    public static final String TOKEN_INVALID = "Token invalid";
    public static final String TOKEN_EXPIRE = "Token expire";
    public static final String TOKEN_UNSUPPORTED = "Token unsupported";
    public static final String MUST_CONTAIN_TOKEN_TYPE = "Token must contain " + Constants.TOKEN_TYPE;
    public static final String INTERNAL_API_NOT_ALLOW = "Not allow access internal api";
}
