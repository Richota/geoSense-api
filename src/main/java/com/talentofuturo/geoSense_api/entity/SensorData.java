package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Entity representing data readings from a Sensor.
 * Stores measurement values and timestamps from sensor devices.
 */
@Entity
@Data
@Setter
@Getter
@Table(name = "sensor_data")
public class SensorData {

    /**
     * Unique identifier for the data reading
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temp")
    private Double temp;
    
    @Column(name = "humidity")
    private Double humidity;
    
    /**
     * Timestamp when the reading was taken
     */
    @Column(name = "datetime", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant datetime; // EPOCH format
    
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
    private Sensor sensor;
}