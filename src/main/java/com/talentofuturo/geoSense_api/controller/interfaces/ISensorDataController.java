package com.talentofuturo.geoSense_api.controller.interfaces;

import java.time.Instant;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;

/**
 * Interface defining the REST endpoints for sensor data operations.
 */
public interface ISensorDataController {
    /**
     * Receives and processes sensor measurements.
     *
     * @param data The DTO containing sensor measurements data
     * @return ResponseEntity with success message if processing is successful
     */
    ResponseEntity<String> receiveMeasurements(SensorDataDTO data);

    /**
     * Retrieves sensor measurements within an optional date range.
     *
     * @param sensorId The ID of the sensor
     * @param startDate Optional start date for filtering measurements
     * @param endDate Optional end date for filtering measurements
     * @return List of sensor measurements matching the criteria
     */
    ResponseEntity<List<SensorData>> getSensorMeasurements(Long sensorId, Instant startDate, Instant endDate);

    /**
     * Retrieves the most recent measurement for a specific sensor.
     *
     * @param sensorId The ID of the sensor
     * @return The latest sensor measurement
     */
    ResponseEntity<SensorData> getLatestMeasurement(Long sensorId);
}