package com.talentofuturo.geoSense_api.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining the contract for company operations.
 * Provides endpoints for managing companies through REST API.
 */
public interface ICompanyController {

    /**
     * Creates a new company in the system.
     *
     * @param adminId    The ID of the admin creating the company.
     * @param companyDTO The company data to be created.
     * @return The created company as DTO.
     */
    ResponseEntity<CompanyDTO> createCompany(@PathVariable Long adminId, @RequestBody CompanyDTO companyDTO);

    /**
     * Updates an existing company in the system.
     *
     * @param companyId  The ID of the company to update.
     * @param companyDTO The updated company data.
     * @return The updated company as DTO.
     */
    ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO companyDTO);

    /**
     * Deletes a company from the system.
     *
     * @param companyId The ID of the company to delete.
     * @return A response indicating the result of the operation.
     */
    ResponseEntity<Void> deleteCompany(@PathVariable Long companyId);

    /**
     * Retrieves all companies managed by a specific admin.
     *
     * @param adminId The ID of the admin.
     * @return A list of companies as DTOs.
     */
    ResponseEntity<List<CompanyDTO>> getAllCompaniesByAdmin(@PathVariable Long adminId);
}