package com.talentofuturo.geoSense_api.controller.interfaces;

import com.talentofuturo.geoSense_api.entity.Company;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Interface defining the contract for administrative operations.
 * Provides endpoints for managing companies through REST API.
 */
public interface IAdminController {
    /**
     * Creates a new company associated with the specified admin.
     *
     * @param adminId The ID of the admin creating the company
     * @param company The company details to be created
     * @return ResponseEntity containing the created company
     */
    ResponseEntity<Company> createCompany(Long adminId, Company company);

    /**
     * Updates the information of an existing company.
     *
     * @param companyId The ID of the company to update
     * @param company The updated company details
     * @return ResponseEntity containing the updated company
     */
    ResponseEntity<Company> updateCompany(Long companyId, Company company);

    /**
     * Deletes a company by its ID.
     *
     * @param companyId The ID of the company to delete
     * @return ResponseEntity with no content on successful deletion
     */
    ResponseEntity<Void> deleteCompany(Long companyId);

    /**
     * Retrieves all companies associated with a specific admin.
     *
     * @param adminId The username of the admin
     * @return ResponseEntity containing a list of companies
     */
    ResponseEntity<List<Company>> getAllCompanies(Long adminId);
}