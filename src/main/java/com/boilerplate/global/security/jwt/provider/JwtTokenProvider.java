package com.boilerplate.global.security.jwt.provider;

import com.boilerplate.global.property.JwtProperty;
import com.boilerplate.global.util.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.boilerplate.global.security.jwt.JwtClaimKey.USERNAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String JWT = "JWT";
    private static final String ISSUER = "Jainwon ATS Builder";
    private static final String HEADER_TYP = "typ";
    private static final String HEADER_ALGORITHM = "alg";

    private final JwtProperty jwtProperty;

    public String createToken(String username, long tokenTimeToLive) {
        Date now = DateUtils.getCurrentDate();
        Date expiredDate = DateUtils.addTime(now, tokenTimeToLive);

        return Jwts.builder()
                .setHeader(createJwtHeader())
                .setClaims(createJwtClaims(username))
                .setIssuedAt(now)
                .setIssuer(ISSUER)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, jwtProperty.signKey())
                .compact();
    }

    private Map<String, Object> createJwtHeader() {
        Map<String, Object> headers = new HashMap<>();
        headers.put(HEADER_TYP, JWT);
        headers.put(HEADER_ALGORITHM, SignatureAlgorithm.HS256.getValue());

        return headers;
    }

    private Map<String, Object> createJwtClaims(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME.getKeyName(), username);

        return claims;
    }

    public Claims parseClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtProperty.signKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
