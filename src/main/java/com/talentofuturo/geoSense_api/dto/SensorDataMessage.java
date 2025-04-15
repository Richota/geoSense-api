package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DTO representing a sensor data message to be sent to Kafka.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataMessage {
    private Long sensorId;
    private Double value;
    private String measurementType;
    private Instant timestamp;
}