package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Entity
@Data
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "timestamp")
    private Instant timestamp = Instant.now();
    private String data;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
