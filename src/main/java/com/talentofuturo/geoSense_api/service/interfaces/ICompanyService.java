package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining operations for company management.
 */
public interface ICompanyService {

    /**
     * Creates a new company.
     *
     * @param adminId    The ID of the admin creating the company.
     * @param companyDTO The company data to create.
     * @return The created company as DTO.
     */
    CompanyDTO createCompany(Long adminId, CompanyDTO companyDTO);

    /**
     * Updates an existing company.
     *
     * @param companyId  The ID of the company to update.
     * @param companyDTO The updated company data.
     * @return The updated company as DTO.
     */
    CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO);

    /**
     * Deletes a company by its ID.
     *
     * @param companyId The ID of the company to delete.
     */
    void deleteCompany(Long companyId);

    /**
     * Retrieves all companies managed by a specific admin.
     *
     * @param adminId The ID of the admin.
     * @return A list of companies as DTOs.
     */
    List<CompanyDTO> getAllCompaniesByAdmin(Long adminId);
}