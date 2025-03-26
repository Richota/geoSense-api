package com.talentofuturo.geoSense_api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentofuturo.geoSense_api.entity.Sensor;

@Repository
/**
 * Repository interface for Sensor entity operations.
 * Provides database access and CRUD operations for Sensor entities.
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    /**
     * Retrieves all sensors installed at a specific location.
     *
     * @param locationId The ID of the location
     * @return List of sensors at the specified location
     */
    List<Sensor> findByLocationId(Long locationId);
}
