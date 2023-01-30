package com.boilerplate.global.security.jwt.provider;

import com.boilerplate.global.security.jwt.JwtClaimKey;
import com.boilerplate.global.security.jwt.UserContext;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    private final JwtTokenProvider jwtTokenProvider;

    public Authentication authenticate(String accessToken) throws AuthenticationException {
        Claims claims = jwtTokenProvider.parseClaims(accessToken);
        String username = claims.get(JwtClaimKey.USERNAME.getKeyName(), String.class);
        UserContext userContext = new UserContext(username, null);

        return new UsernamePasswordAuthenticationToken(userContext, null, null);
    }
}
