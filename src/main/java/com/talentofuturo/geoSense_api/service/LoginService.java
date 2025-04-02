package com.talentofuturo.geoSense_api.service;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.talentofuturo.geoSense_api.dto.AuthLoginRequest;
import com.talentofuturo.geoSense_api.dto.LoginDTO;
import com.talentofuturo.geoSense_api.security.jwt.JwtUtils;
import com.talentofuturo.geoSense_api.service.interfaces.ILoginService;

public class LoginService implements ILoginService {

    private JwtUtils jwtUtils;

    public LoginDTO loginAdmin(AuthLoginRequest adminRequest) {
        String username = adminRequest.getUsername();
        String password = adminRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        try {
            String jwt = jwtUtils.createToken(authentication);
            return new LoginDTO(username, "Login successful", jwt, true);
        } catch (Exception e) {
            return new LoginDTO(username, "Login unsuccessful", null, false);
        }

    }

    private Authentication authenticate(String username, String password) {
        if (!username.equals("admin") || !password.equals("123")) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String authoritiesList = "ROLE_ADMIN,CREATE,READ,UPDATE,DELETE";
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesList);
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

}
