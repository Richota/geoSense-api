package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for Sensor entity, without circular references.
 * Es inmutable para garantizar la integridad de los datos.
 */
@Getter
@AllArgsConstructor
public class SensorDTO {
    /**
     * Unique identifier for the sensor
     */
    private final Long id;

    /**
     * Name of the sensor
     */
    private final String sensorName;

    /**
     * Category of the sensor
     */
    private final String sensorCategory;

    /**
     * Additional metadata about the sensor
     */
    private final String sensorMeta;

    /**
     * API key for the sensor
     */
    private final String sensorApiKey;

    /**
     * API key for the company
     */
    private final String companyApiKey;

    /**
     * ID of the location associated with the sensor
     */
    private final Long locationId;

    /**
     * Name of the location associated with the sensor
     */
    private final String locationName;
}
