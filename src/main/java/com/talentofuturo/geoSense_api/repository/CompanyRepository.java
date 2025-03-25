package com.talentofuturo.geoSense_api.repository;

import com.talentofuturo.geoSense_api.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Company entity operations.
 * Provides database access and CRUD operations for Company entities.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * Retrieves all companies associated with a specific admin username.
     *
     * @param adminUsername The username of the admin
     * @return List of companies managed by the specified admin
     */
    List<Company> findByAdminUsername(String adminUsername);
}
