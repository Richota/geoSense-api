package com.talentofuturo.geoSense_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // SecurityFilterChain
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/register").hasRole("ADMIN")
                .anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/auth/login", "/api/auth/register",
                        "/api/auth/change-password", "/logout"))
                .formLogin(form -> form
                        .successHandler(customAthenticationSuccessHandler()))
                .httpBasic(withDefaults());

        return http.build();
    }

    // customAthenticationSuccessHandler
    @Bean
    AuthenticationSuccessHandler customAthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        };
    }

    // AuthenticationManager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // UserDetailsService
    // @Bean
    // UserDetailsService userDetailsService() {
    // UserDetails user1 = User.builder()
    // .username("user")
    // .password(passwordEncoder().encode("password"))
    // .roles("ADMIN")
    // .build();
    //
    // UserDetails user2 = User.builder()
    // .username("user2")
    // .password(passwordEncoder().encode("password"))
    // .roles("USER")
    // .build();
    //
    // return new InMemoryUserDetailsManager(user1, user2);
    // }

    // PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
