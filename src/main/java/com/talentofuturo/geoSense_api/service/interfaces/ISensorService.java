package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.entity.Sensor;

/**
 * Interface defining operations for managing sensors.
 */
public interface ISensorService {

    /**
     * Retrieves a sensor by its ID.
     *
     * @param sensorId The ID of the sensor.
     * @return The sensor entity.
     */
    Sensor getSensorById(Long sensorId);

    /**
     * Creates a new sensor associated with a location and a company.
     *
     * @param companyApiKey The API key of the company.
     * @param locationId    The ID of the location.
     * @param sensor        The sensor data to create.
     * @return The created sensor entity.
     */
    Sensor createSensor(String companyApiKey, Long locationId, Sensor sensor);

    /**
     * Updates an existing sensor.
     *
     * @param sensorId The ID of the sensor to update.
     * @param sensor   The updated sensor data.
     * @return The updated sensor entity.
     */
    Sensor updateSensor(Long sensorId, Sensor sensor);

    /**
     * Deletes a sensor by its ID.
     *
     * @param sensorId The ID of the sensor to delete.
     */
    void deleteSensor(Long sensorId);

    /**
     * Retrieves all sensors.
     *
     * @return A list of all sensors.
     */
    List<Sensor> getAllSensors();

    /**
     * Retrieves all sensors belonging to a specific company.
     *
     * @param companyApiKey The API key of the company.
     * @return A list of sensors belonging to the specified company.
     */
    List<Sensor> getSensorsByCompany(String companyApiKey);

    /**
     * Retrieves all sensors installed at a specific location.
     *
     * @param locationId The ID of the location.
     * @return A list of sensors at the specified location.
     */
    List<Sensor> getSensorsByLocation(Long locationId);

    /**
     * Retrieves a sensor by its API key.
     *
     * @param sensorApiKey The API key of the sensor.
     * @return The sensor entity if found, or null if not found.
     */
    Sensor getSensorByApiKey(String sensorApiKey);
}