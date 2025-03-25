package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import java.util.List;

/**
 * Interface defining the contract for company operations.
 * Provides endpoints for managing companies through REST API.
 */
public interface ICompanyController {
    /**
     * Retrieves all companies in the system.
     *
     * @return List of all companies as DTOs
     */
    List<CompanyDTO> getAllCompanies();

    /**
     * Creates a new company in the system.
     *
     * @param companyDTO The company data to be created
     * @return The created company as DTO
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);
}