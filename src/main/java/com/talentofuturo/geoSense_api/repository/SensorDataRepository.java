package com.talentofuturo.geoSense_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentofuturo.geoSense_api.entity.SensorData;

@Repository
/**
 * Repository interface for SensorData entity operations.
 * Provides database access and CRUD operations for sensor readings and measurements.
 */
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    /**
     * Retrieves all data readings from a specific sensor.
     *
     * @param sensorId The ID of the sensor
     * @return List of data readings from the specified sensor
     */
    List<SensorData> findBySensorId(Long sensorId);
}
