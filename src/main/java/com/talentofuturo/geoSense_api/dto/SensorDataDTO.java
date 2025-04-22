package com.talentofuturo.geoSense_api.dto;

import lombok.Data;

import java.time.Instant;

/**
 * DTO representing data readings from a Sensor.
 */
@Data
public class SensorDataDTO {

    private Long id; // Unique identifier for the data reading
    private Double apiKey; // Value recorded by the sensor
    private Instant timestart; // Start timestamp of the reading
    private Instant timeend; // End timestamp of the reading
    private String measurementType1; // First type or unit of measurement
    private String measurementType2; // Second type or unit of measurement
    private String measurementType3; // Third type or unit of measurement
    private Long sensorId; // ID of the sensor that generated this data
}