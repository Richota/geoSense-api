package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity representing data readings from a Sensor.
 * Stores measurement values and timestamps from sensor devices.
 */
@Entity
@Data
@Table(name = "sensor_data")
public class SensorData {

    /**
     * Unique identifier for the data reading
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Value recorded by the sensor
     */
    @Column(name = "value", nullable = false)
    @NotNull(message = "Sensor data value cannot be null")
    private Double value;

    /**
     * Timestamp when the reading was taken
     */
    @Column(name = "timestamp", nullable = false)
    @NotNull(message = "Timestamp cannot be null")
    private Instant timestamp;

    /**
     * Type or unit of measurement
     */
    @Column(name = "measurement_type", nullable = false)
    @NotNull(message = "Measurement type cannot be null")
    private String measurementType;

    /**
     * Sensor that generated this data
     * Many-to-one relationship with Sensor entity
     */
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    @JsonIgnore
    private Sensor sensor;
}