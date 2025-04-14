package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.dto.SensorDTO;

/**
 * Interface defining operations for sensor management.
 */
public interface ISensorService {
    /**
     * Retrieves all sensors in the system.
     *
     * @return List of all sensors as DTOs
     */
    List<SensorDTO> getAllSensors();

    /**
     * Creates a new sensor.
     *
     * @param sensorDTO The sensor data to create
     * @return The created sensor as DTO
     */
    SensorDTO createSensor(SensorDTO sensorDTO);

    SensorDTO updateSensor(Long id, SensorDTO sensorDTO);
    void deleteSensor(Long id);
}