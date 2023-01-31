package com.boilerplate.global.exception;

import org.springframework.security.core.AuthenticationException;

public class FailedAuthenticationException extends AuthenticationException {

    public FailedAuthenticationException() {
        super("인증에 실패하였습니다.");
    }
}
