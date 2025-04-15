package com.talentofuturo.geoSense_api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.AuthLoginRequest;
import com.talentofuturo.geoSense_api.dto.AuthLoginResponse;
import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.exception.InvalidCredentialsException;
import com.talentofuturo.geoSense_api.exception.UserNotFoundException;
import com.talentofuturo.geoSense_api.repository.AdminRepository;
import com.talentofuturo.geoSense_api.security.jwt.JwtUtils;
import com.talentofuturo.geoSense_api.service.interfaces.ILoginService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of ILoginService for handling admin login operations.
 */
@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private static final String DEFAULT_AUTHORITIES = "ROLE_ADMIN,CREATE,READ,UPDATE,DELETE";

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    /**
     * Handles the login process for an admin.
     *
     * @param adminRequest The login request containing username and password.
     * @return An AuthLoginResponse containing the result of the login attempt.
     */
    public AuthLoginResponse loginAdmin(AuthLoginRequest adminRequest) {
        String username = adminRequest.getUsername();
        String password = adminRequest.getPassword();

        try {
            logger.info("Authenticating user: {}", username);

            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("User authenticated successfully: {}", username);

            String jwt = jwtUtils.createToken(authentication);
            logger.info("JWT generated for user: {}", username);

            // Corrige el orden de los parámetros
            return new AuthLoginResponse(username, "Login successful", jwt, true);
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed for user: {} - {}", username, e.getMessage());
            return new AuthLoginResponse(username, "Invalid username or password", null, false);
        } catch (Exception e) {
            logger.error("Unexpected error during login for user: {} - {}", username, e.getMessage());
            return new AuthLoginResponse(username, "An unexpected error occurred", null, false);
        }
    }

    /**
     * Authenticates the user by validating their credentials.
     *
     * @param username The username of the admin.
     * @param password The password of the admin.
     * @return An Authentication object if the credentials are valid.
     */
    private Authentication authenticate(String username, String password) {
        // Busca al administrador en la base de datos
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));

        // Valida la contraseña
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        // Define roles y permisos
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(DEFAULT_AUTHORITIES);

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}