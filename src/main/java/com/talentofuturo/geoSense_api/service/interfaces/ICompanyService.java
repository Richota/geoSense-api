package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining operations for company management.
 */
public interface ICompanyService {
    /**
     * Retrieves all companies in the system.
     *
     * @return List of all companies
     */
    List<CompanyDTO> getAllCompanies();

    /**
     * Creates a new company.
     *
     * @param companyDTO The company data to create
     * @return The created company as DTO
     */
    CompanyDTO createCompany(CompanyDTO companyDTO);

    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    void deleteCompany(Long id);
}