package com.talentofuturo.geoSense_api.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SensorDataDTO {

    private Long id;
    private Double apiKey;
    private Instant timestart;
    private Instant timeend;
    private String measurementType1;
    private String measurementType2;
    private String measurementType3;
}