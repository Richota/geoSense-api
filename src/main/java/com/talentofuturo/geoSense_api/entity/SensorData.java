package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Entity
@Data
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant timestamp = Instant.now();
    private String data; // Almacenará el JSON de datos del sensor

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}