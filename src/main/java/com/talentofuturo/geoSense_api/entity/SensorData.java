package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

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
    @Column(name = "value")
    private Double value;
    
    /**
     * Timestamp when the reading was taken
     */
    @Column(name = "timestamp")
    private Instant timestamp;
    
    /**
     * Type or unit of measurement
     */
    @Column(name = "measurement_type")
    private String measurementType;
    
    /**
     * Sensor that generated this data
     * Many-to-one relationship with Sensor entity
     */
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
