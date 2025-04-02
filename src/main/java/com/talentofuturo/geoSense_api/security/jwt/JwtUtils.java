package com.talentofuturo.geoSense_api.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    @Value("${secret.private.key.jwt}")
    private String PRIVATE_KEY;

    private String USER_GENERATOR;

    private final static int EXPIRATION_OFFSET = 1_800_000;
    private final static int NOT_BEFORE_OFFSET = 1_000;

    public String createToken(Authentication authentication) throws Exception {
        final String username = authentication.getName();
        final String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(USER_GENERATOR)
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_OFFSET))
                .setNotBefore(new Date(System.currentTimeMillis() + NOT_BEFORE_OFFSET))
                .setId(UUID.randomUUID().toString())
                .signWith(getSigninKey())
                .compact();
    }

    public Jws<Claims> parseToken(String token) throws Exception {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token);
    }

    public String extractUserName(Jws<Claims> decodedToken) {
        return decodedToken.getBody().getSubject();
    }

    public String extractClaim(Jws<Claims> decodedToken, String claimName) {
        return decodedToken.getBody().get(claimName, String.class);
    }

    public Claims extractClaims(Jws<Claims> decodedToken) {
        return decodedToken.getBody();
    }

    public Key getSigninKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(PRIVATE_KEY), SignatureAlgorithm.HS256.getJcaName());
    }
}
