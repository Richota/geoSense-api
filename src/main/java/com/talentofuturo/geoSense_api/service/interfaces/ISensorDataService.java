package com.talentofuturo.geoSense_api.service.interfaces;

import java.time.Instant;
import java.util.List;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;

/**
 * Service interface for handling sensor data operations.
 */
public interface ISensorDataService {
    /**
     * Processes incoming sensor data measurements.
     * Validates the sensor API key and measurement values before saving.
     *
     * @param sensorDataDTO DTO containing sensor measurements
     * @throws InvalidSensorException if the sensor API key is invalid
     * @throws InvalidDataException if temperature or humidity values are out of valid range
     */
    void processSensorData(SensorDataDTO sensorDataDTO);

    /**
     * Retrieves sensor measurements for a specific sensor, optionally filtered by date range.
     *
     * @param sensorId ID of the sensor
     * @param startDate Optional start date for filtering measurements
     * @param endDate Optional end date for filtering measurements
     * @return List of sensor measurements matching the criteria
     */
    List<SensorData> getMeasurements(Long sensorId, Instant startDate, Instant endDate);

    /**
     * Retrieves the most recent measurement for a specific sensor.
     *
     * @param sensorId ID of the sensor
     * @return The latest sensor measurement
     * @throws RuntimeException if no measurements are found for the sensor
     */
    SensorData getLatestMeasurement(Long sensorId);
}