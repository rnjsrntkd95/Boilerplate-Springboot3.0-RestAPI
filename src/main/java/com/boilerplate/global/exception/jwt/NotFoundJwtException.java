package com.boilerplate.global.exception.jwt;

import io.jsonwebtoken.JwtException;

public class NotFoundJwtException extends JwtException {

    public NotFoundJwtException() {
        super("JWT 토큰을 찾을 수 없습니다.");
    }
}
