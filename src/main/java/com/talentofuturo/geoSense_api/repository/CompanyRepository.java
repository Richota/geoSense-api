package com.talentofuturo.geoSense_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentofuturo.geoSense_api.entity.Company;

/**
 * Repository interface for Company entity operations.
 * Provides database access and CRUD operations for Company entities.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * Retrieves all companies associated with a specific admin username.
     *
     * @param id The id of the admin
     * @return List of companies managed by the specified admin
     */
    List<Company> findByAdminId(Long admin_id);
        // Método para buscar una empresa por su clave API
        Company findByCompanyApiKey(String companyApiKey);
}
