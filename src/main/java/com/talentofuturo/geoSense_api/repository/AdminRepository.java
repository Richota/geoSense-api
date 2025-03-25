package com.talentofuturo.geoSense_api.repository;

import com.talentofuturo.geoSense_api.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Admin entity operations.
 * Provides database access and CRUD operations for Admin entities.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    /**
     * Finds an admin by their username.
     *
     * @param username The username to search for
     * @return Optional containing the admin if found, empty Optional otherwise
     */
    Optional<Admin> findByUsername(String username);
}
