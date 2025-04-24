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
@SequenceGenerator(name = "sensor_data_seq", sequenceName = "sensor_data_seq", allocationSize = 1)
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_data_seq")
    private Long id;

    @Column(name = "apiKey", nullable = false, unique = true)
    @NotNull(message = "Sensor data api_key cannot be null")
    private Double apiKey;

    @Column(name = "timestart", nullable = false)
    private Instant timestart;

    @Column(name = "timeend", nullable = false)
    private Instant timeend;

    @Column(name = "measurement_type1", nullable = false)
    @NotNull(message = "Measurement type cannot be null")
    private String measurementType1;

    @Column(name = "measurement_type2", nullable = false)
    @NotNull(message = "Measurement type cannot be null")
    private String measurementType2;

    @Column(name = "measurement_type3", nullable = false)
    @NotNull(message = "Measurement type cannot be null")
    private String measurementType3;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    @JsonIgnore
    private Sensor sensor;

    public void setMeasurementType(String measurementType) {
        this.measurementType1 = measurementType;
    }

    public void setApiKey(Double apiKey) {
        this.apiKey = apiKey;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestart = timestamp;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}