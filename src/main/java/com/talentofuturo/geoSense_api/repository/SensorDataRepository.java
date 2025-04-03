package com.talentofuturo.geoSense_api.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
     * Finds all sensor measurements within a specified time range for a given sensor.
     *
     * @param sensorId The ID of the sensor
     * @param startDate The start of the time range
     * @param endDate The end of the time range
     * @return List of sensor measurements within the specified time range
     */
    List<SensorData> findBySensorIdAndDatetimeBetween(Long sensorId, Instant startDate, Instant endDate);

    /**
     * Retrieves all measurements for a specific sensor.
     *
     * @param sensorId The ID of the sensor
     * @return List of all measurements for the specified sensor
     */
    List<SensorData> findBySensorId(Long sensorId);

    /**
     * Finds the most recent measurement for a specific sensor.
     *
     * @param sensorId The ID of the sensor
     * @return Optional containing the most recent measurement, or empty if no measurements exist
     */
    Optional<SensorData> findFirstBySensorIdOrderByDatetimeDesc(Long sensorId);
}
