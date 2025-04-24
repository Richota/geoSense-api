package com.talentofuturo.geoSense_api.service.interfaces;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;

import java.time.Instant;
import java.util.List;

public interface ISensorDataService {

    /**
     * Save sensor data using sensor_api_key for authorization.
     *
     * @param sensorApiKey  The API key of the sensor.
     * @param sensorDataDTO The sensor data to save.
     * @return The saved sensor data.
     */
    SensorDataDTO saveSensorData(String sensorApiKey, SensorDataDTO sensorDataDTO);

    /**
     * Query sensor data with filters.
     *
     * @param companyApiKey The API key of the company.
     * @param from          The start timestamp.
     * @param to            The end timestamp.
     * @param sensorIds     The list of sensor IDs to query.
     * @return A list of sensor data matching the filters.
     */
    List<SensorDataDTO> querySensorData(String companyApiKey, Instant from, Instant to, List<Long> sensorIds);
}