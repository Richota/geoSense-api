package com.talentofuturo.geoSense_api.controller.interfaces;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining the contract for company operations.
 * Provides endpoints for managing companies through REST API.
 */
public interface ICompanyController {

    /**
     * Creates a new company in the system.
     *
     * @param companyDTO The company data to be created
     * @return The created company as DTO
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);
}