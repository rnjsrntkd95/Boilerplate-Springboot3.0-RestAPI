package com.boilerplate.global.exception.jwt;

import org.springframework.security.core.AuthenticationException;

public class FailedJwtAuthenticationException extends AuthenticationException {

    public FailedJwtAuthenticationException() {
        super("JWT 인증에 실패하였습니다.");
    }
}
