package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.repository.AdminRepository;
import com.talentofuturo.geoSense_api.service.interfaces.IAdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;

    /**
     * Verifica si un administrador existe por su ID.
     *
     * @param adminId ID del administrador.
     * @return true si el administrador existe, de lo contrario false.
     */
    public boolean existsById(Long adminId) {
        return adminRepository.existsById(adminId);
    }
}