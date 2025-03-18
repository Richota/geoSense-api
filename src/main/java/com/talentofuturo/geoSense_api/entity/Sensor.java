package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Entity
@Data
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "sensorName")
    private String sensorName;
    
    @Column(name = "sensorCategory")
    private String sensorCategory;
    
    @Column(name = "sensorMeta")
    private String sensorMeta;
    
    @Column(name = "sensorApiKey")
    private String sensorApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
