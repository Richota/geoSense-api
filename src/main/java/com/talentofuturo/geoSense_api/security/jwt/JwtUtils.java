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

import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.repository.AdminRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);

    private final AdminRepository adminRepository;

    @Value("${secret.private.key.jwt}")
    private String PRIVATE_KEY;
    @Value("${secret.private.admin.jwt}")
    private String USER_GENERATOR;

    private final static int EXPIRATION_OFFSET = 1_800_000; // 30 minutos
    private final static int NOT_BEFORE_OFFSET = 1_000; // 1 segundo

    public JwtUtils(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Crea un token JWT para un usuario autenticado.
     *
     * @param authentication Información de autenticación del usuario.
     * @return Token JWT generado.
     * @throws Exception Si ocurre un error durante la generación del token.
     */
    public String createToken(Authentication authentication) throws Exception {
        try {
            final String username = authentication.getName();
            final String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            // Busca al administrador por su nombre de usuario
            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            String token = Jwts.builder()
                    .setIssuer(USER_GENERATOR)
                    .setSubject(username)
                    .claim("authorities", authorities)
                    .claim("adminId", admin.getId()) // Agrega el ID del administrador como claim
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

    /**
     * Decodifica un token JWT.
     *
     * @param token Token JWT a decodificar.
     * @return Claims decodificados del token.
     * @throws Exception Si ocurre un error durante la decodificación.
     */
    public Jws<Claims> parseToken(String token) throws Exception {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token);
    }

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param decodedToken Token decodificado.
     * @return Nombre de usuario.
     */
    public String extractUserName(Jws<Claims> decodedToken) {
        return decodedToken.getBody().getSubject();
    }

    /**
     * Extrae un claim específico del token JWT.
     *
     * @param decodedToken Token decodificado.
     * @param claimName    Nombre del claim a extraer.
     * @return Valor del claim.
     */
    public String extractClaim(Jws<Claims> decodedToken, String claimName) {
        return decodedToken.getBody().get(claimName, String.class);
    }

    /**
     * Extrae el ID del administrador del token JWT.
     *
     * @param decodedToken Token decodificado.
     * @return ID del administrador.
     */
    public Long extractAdminId(Jws<Claims> decodedToken) {
        return decodedToken.getBody().get("adminId", Long.class);
    }

    /**
     * Extrae todos los claims del token JWT.
     *
     * @param decodedToken Token decodificado.
     * @return Claims del token.
     */
    public Claims extractClaims(Jws<Claims> decodedToken) {
        return decodedToken.getBody();
    }

    /**
     * Obtiene la clave de firma para el token JWT.
     *
     * @return Clave de firma.
     */
    public Key getSigninKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(PRIVATE_KEY), SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Valida un token JWT.
     *
     * @param decodedToken Token decodificado.
     * @param userDetails  Detalles del usuario autenticado.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean isTokenValid(Jws<Claims> decodedToken, UserDetails userDetails) {
        try {
            // Extrae el nombre de usuario del token
            String username = extractUserName(decodedToken);

            // Verifica si el nombre de usuario coincide con el de los detalles del usuario
            return username.equals(userDetails.getUsername()) &&
                    !decodedToken.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            logger.error("Error validating token: {}", e.getMessage());
            return false;
        }
    }
}