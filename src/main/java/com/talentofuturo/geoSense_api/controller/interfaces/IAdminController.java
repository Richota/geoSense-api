package com.talentofuturo.geoSense_api.controller.interfaces;

import org.springframework.http.ResponseEntity;

/**
 * Interface defining the contract for administrative operations.
 * Provides endpoints for managing companies through REST API.
 */
public interface IAdminController {

    // ResponseEntity<String> getRuta();

    ResponseEntity<String> getAdmin(Long adminId);

}