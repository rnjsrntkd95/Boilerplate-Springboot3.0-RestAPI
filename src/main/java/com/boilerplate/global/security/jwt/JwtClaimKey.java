package com.boilerplate.global.security.jwt;

import lombok.Getter;

@Getter
public enum JwtClaimKey {

    USERNAME("username"),
    ;

    private final String keyName;

    JwtClaimKey(String keyName) {
        this.keyName = keyName;
    }
}
