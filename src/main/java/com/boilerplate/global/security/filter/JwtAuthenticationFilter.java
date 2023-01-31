package com.boilerplate.global.security.filter;

import com.boilerplate.global.exception.jwt.NotFoundJwtException;
import com.boilerplate.global.security.jwt.JwtExceptionCode;
import com.boilerplate.global.security.jwt.provider.JwtAuthenticationProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_TOKEN_REGEX = "Bearer (.*)";
    public static final String JWT_EXCEPTION = "exception";
    private static final int GROUP_FIRST = 1;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            authenticate(authorization);
        } catch (ExpiredJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.EXPIRED_TOKEN);
        } catch (SignatureException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID_SIGNATURE);
        } catch (UnsupportedJwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.UNSUPPORTED_TOKEN);
        } catch (JwtException e) {
            request.setAttribute(JWT_EXCEPTION, JwtExceptionCode.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String authorization) {
        String accessToken = Optional
                .ofNullable(authorization)
                .map(this::getAccessToken)
                .orElse(null);

        if (StringUtils.isNotBlank(accessToken)) {
            Authentication auth = jwtAuthenticationProvider.authenticate(accessToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String getAccessToken(String bearerToken) {
        Pattern pattern = Pattern.compile(BEARER_TOKEN_REGEX);
        Matcher matcher = pattern.matcher(bearerToken);

        if (matcher.find()) {
            return matcher.group(GROUP_FIRST);
        }
        throw new NotFoundJwtException();
    }
}
