package com.talentofuturo.geoSense_api.repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves all sensors belonging to a specific company.
     *
     * @param companyApiKey The API key of the company
     * @return List of sensors belonging to the specified company
     */
    List<Sensor> findByCompanyApiKey(String companyApiKey);

    /**
     * Finds a sensor by its API key.
     *
     * @param sensorApiKey The API key of the sensor
     * @return Optional containing the sensor if found, empty otherwise
     */
    Optional<Sensor> findBySensorApiKey(String sensorApiKey);

}