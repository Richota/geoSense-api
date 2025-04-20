package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Entity representing a Sensor device in the system.
 * Sensors are associated with companies and have unique API keys for
 * authentication.
 */
@Entity
@Data
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_seq")
    private Long id;

    @Column(name = "sensor_name", nullable = false)
    private String sensorName;

    @Column(name = "sensor_category", nullable = false)
    private String sensorCategory;

    @Column(name = "sensor_meta")
    private String sensorMeta;

    @Column(name = "sensor_api_key", nullable = false, unique = true, updatable = false)
    private String sensorApiKey;

    @Column(name = "company_api_key", nullable = false)
    private String companyApiKey;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @PrePersist
    private void generateApiKey() {
        this.sensorApiKey = UUID.randomUUID().toString();
    }
}