package com.talentofuturo.geoSense_api.service.interfaces;

/**
 * Interface defining administrative operations for managing admins.
 */
public interface IAdminService {

    /**
     * Verifica si un administrador existe por su ID.
     *
     * @param adminId ID del administrador.
     * @return true si el administrador existe, de lo contrario false.
     */
    boolean existsById(Long adminId);
}