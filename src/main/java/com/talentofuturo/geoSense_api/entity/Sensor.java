package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Entity representing a Sensor device in the system.
 * Sensors are associated with locations and have unique API keys for authentication.
 */
@Entity
@Data
@Table(name = "sensors")
public class Sensor {
    /**
     * Unique identifier for the sensor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name or identifier of the sensor
     */
    @Column(name = "sensorName", nullable = false)
    @NotNull(message = "Sensor name cannot be null")
    @Size(min = 3, max = 50, message = "Sensor name must be between 3 and 50 characters")
    private String sensorName;

    /**
     * Category or type of the sensor
     */
    @Column(name = "sensorCategory")
    private String sensorCategory;
    
    /**
     * Additional status or configuration for the sensor
     */
    @Column(name = "sensorStatus")
    private String sensorStatus;
    
    /**
     * Latitude where the sensor is
     */
    @Column(name = "sensorLatitude")
    private String sensorLatitude;

    /**
     * Longitude where the sensor is
     */
    @Column(name = "sensorLongitude")
    private String sensorLongitude;
    
    /**
     * Additional metadata or configuration for the sensor
     */
    @Column(name = "sensorMeta")
    private String sensorMeta;

    /**
     * Unique API key for sensor authentication
     * Automatically generated using UUID
     */
    @Column(name = "sensorApiKey")
    private String sensorApiKey = UUID.randomUUID().toString();

    /**
     * Location where this sensor is installed
     * Many-to-one relationship with Location entity
     */
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NotNull(message = "Sensor must be associated with a location")
    private Location location;
    
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<SensorData> sensorData; 
}
