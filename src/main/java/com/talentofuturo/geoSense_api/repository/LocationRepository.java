package com.talentofuturo.geoSense_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentofuturo.geoSense_api.entity.Location;

@Repository
/**
 * Repository interface for Location entity operations.
 * Provides database access and CRUD operations for Location entities.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     * Retrieves all locations associated with a specific company.
     *
     * @param companyId The ID of the company
     * @return List of locations belonging to the specified company
     */
    List<Location> findByCompanyId(Long companyId);
}
