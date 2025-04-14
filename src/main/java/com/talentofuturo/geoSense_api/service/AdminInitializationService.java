package com.talentofuturo.geoSense_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.repository.AdminRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminInitializationService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = Admin.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123")) // Cifra la contraseña
                    .build();
            adminRepository.save(admin);
            System.out.println("Admin user created: admin / admin123");
        }
    }
}