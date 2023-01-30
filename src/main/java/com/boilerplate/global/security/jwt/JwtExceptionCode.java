package com.boilerplate.global.security.jwt;

import lombok.Getter;

@Getter
public enum JwtExceptionCode {

    EXPIRED_TOKEN("ExpiredJwtException", "유효기간이 만료된 JWT 토큰입니다."),
    INVALID_SIGNATURE("InvalidJwtSignatureException", "유효하지 않은 JWT 서명입니다."),
    UNSUPPORTED_TOKEN("UnsupportedJwtException", "지원하지 않는 형식의 JWT 토큰입니다."),
    INVALID_TOKEN("InvalidJwtException", "유효하지 않은 JWT 토큰입니다."),
    ;

    private final String code;
    private final String message;

    JwtExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
