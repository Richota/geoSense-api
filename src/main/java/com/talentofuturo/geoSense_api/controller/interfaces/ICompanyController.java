package com.talentofuturo.geoSense_api.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining the contract for CompanyController.
 */
public interface ICompanyController {

    /**
     * Creates a new company associated with an admin.
     *
     * @param adminId    The ID of the admin.
     * @param companyDTO The company data to create.
     * @return The created company as a DTO.
     */
    ResponseEntity<CompanyDTO> createCompany(@PathVariable Long adminId, @RequestBody CompanyDTO companyDTO);

    /**
     * Updates an existing company.
     *
     * @param companyId  The ID of the company to update.
     * @param companyDTO The updated company data.
     * @return The updated company as a DTO.
     */
    ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO companyDTO);

    /**
     * Deletes a company by its ID.
     *
     * @param companyId The ID of the company to delete.
     * @return A response indicating the result of the operation.
     */
    ResponseEntity<Void> deleteCompany(@PathVariable Long companyId);

    /**
     * Retrieves all companies associated with a specific admin.
     *
     * @param adminId The ID of the admin.
     * @return A list of companies as DTOs.
     */
    ResponseEntity<List<CompanyDTO>> getAllCompaniesByAdmin(@PathVariable Long adminId);
}