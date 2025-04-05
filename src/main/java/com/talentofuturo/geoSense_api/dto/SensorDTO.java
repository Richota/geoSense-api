package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object for Sensor information.
 * It transfers sensor data between layers of the application.
 */
@Getter
@AllArgsConstructor
public class SensorDTO {
    /**
     * Unique identifier for the sensor
     */
    private Long id;

    /**
     * Name of the sensor
     */
    private String sensorName;

    /**
     * Type or model of the sensor
     */
    private String sensorCategory;

    /**
     * Current status of the sensor
     */
    private String sensorStatus;
    
    /**
     * Current Latitude of the sensor
     */
    private String sensorLatitude;
    
    /**
     * Current Longitude of the sensor
     */
    private String sensorLongitude;

    /**
     * Additional metadata about the sensor
     */
    private String sensorMeta;
    
    /**
     * Additional apiKey about the sensor
     */
    private String sensorApiKey;
}
