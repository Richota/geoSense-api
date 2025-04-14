package com.talentofuturo.geoSense_api.security.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);

    @Value("${secret.private.key.jwt}")
    private String PRIVATE_KEY;
    @Value("${secret.private.admin.jwt}")
    private String USER_GENERATOR;

    private final static int EXPIRATION_OFFSET = 1_800_000;
    private final static int NOT_BEFORE_OFFSET = 1_000;

    public String createToken(Authentication authentication) throws Exception {
        try {
            final String username = authentication.getName();
            final String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            String token = Jwts.builder()
                    .setIssuer(USER_GENERATOR)
                    .setSubject(username)
                    .claim("authorities", authorities)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_OFFSET))
                    .setNotBefore(new Date(System.currentTimeMillis() + NOT_BEFORE_OFFSET))
                    .setId(UUID.randomUUID().toString())
                    .signWith(getSigninKey())
                    .compact();

            logger.info("JWT Token generated successfully for user: {}", username);
            return token;
        } catch (Exception e) {
            logger.error("Error generating JWT token for user: {} - {}", authentication.getName(), e.getMessage());
            throw e;
        }
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

    public boolean isTokenValid(Jws<Claims> decodedToken, UserDetails userDetails) {
        try {
            // Extrae el nombre de usuario del token
            String username = extractUserName(decodedToken);

            // Verifica que el nombre de usuario del token coincida con el del UserDetails
            // y que el token no haya expirado
            return username.equals(userDetails.getUsername()) &&
                    !decodedToken.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            System.err.println("Error validating token: " + e.getMessage());
            return false;
        }
    }
}