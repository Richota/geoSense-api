package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
@Entity
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sensorName;
    private String sensorCategory;
    private String sensorMeta;
    private String sensorApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
