package com.sudo248.authservice.repository.entity;

public enum Provider {
    AUTH_SERVICE("Auth-service"),
    GOOGLE("Google"),
    FACEBOOK("Facebook"),
    PHONE("phone");

    private final String value;
    private Provider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
