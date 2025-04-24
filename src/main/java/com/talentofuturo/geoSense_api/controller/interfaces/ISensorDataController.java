package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ISensorDataController {

    /**
     * Insert sensor data using sensor_api_key for authorization.
     *
     * @param sensorApiKey  The API key of the sensor.
     * @param sensorDataDTO The sensor data to insert.
     * @return The inserted sensor data.
     */
    ResponseEntity<SensorDataDTO> insertSensorData(@RequestHeader("sensor_api_key") String sensorApiKey,
            @RequestBody SensorDataDTO sensorDataDTO);

    /**
     * Query sensor data with filters.
     *
     * @param companyApiKey The API key of the company.
     * @param from          The start timestamp in EPOCH format.
     * @param to            The end timestamp in EPOCH format.
     * @param sensorIds     The list of sensor IDs to query.
     * @return A list of sensor data matching the filters.
     */
    ResponseEntity<List<SensorDataDTO>> querySensorData(@RequestParam("company_api_key") String companyApiKey,
            @RequestParam("from") long from,
            @RequestParam("to") long to,
            @RequestParam("sensor_id") List<Long> sensorIds);
}