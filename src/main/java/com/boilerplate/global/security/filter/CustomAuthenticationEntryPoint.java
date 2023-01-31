package com.boilerplate.global.security.filter;

import com.boilerplate.global.exception.ErrorRs;
import com.boilerplate.global.exception.FailedAuthenticationException;
import com.boilerplate.global.security.jwt.JwtExceptionCode;
import com.boilerplate.global.util.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import static com.boilerplate.global.security.filter.JwtAuthenticationFilter.JWT_EXCEPTION;
import static com.boilerplate.global.security.jwt.JwtExceptionCode.EXPIRED_TOKEN;
import static com.boilerplate.global.security.jwt.JwtExceptionCode.INVALID_SIGNATURE;
import static com.boilerplate.global.security.jwt.JwtExceptionCode.UNSUPPORTED_TOKEN;
import static io.jsonwebtoken.lang.Strings.UTF_8;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        JwtExceptionCode exceptionCode = (JwtExceptionCode) request.getAttribute(JWT_EXCEPTION);
        ErrorRs errorRs = toErrorRs(exceptionCode);
        setExceptionResponse(response, errorRs);
    }

    private void setExceptionResponse(HttpServletResponse response, ErrorRs errorRs) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setCharacterEncoding(UTF_8.toString());
        response.getWriter().write(JsonUtils.toJson(errorRs));
    }

    private ErrorRs toErrorRs(JwtExceptionCode exceptionCode) {
        if (EXPIRED_TOKEN.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else if (INVALID_SIGNATURE.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else if (UNSUPPORTED_TOKEN.equals(exceptionCode)) {
            return ErrorRs.of(exceptionCode.getCode(), exceptionCode.getMessage());
        } else {
            return ErrorRs.ofException(new FailedAuthenticationException());
        }
    }
}