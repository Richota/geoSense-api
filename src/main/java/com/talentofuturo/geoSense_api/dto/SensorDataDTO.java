package com.talentofuturo.geoSense_api.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SensorDataDTO {
    private String sensorApiKey; 
    private List<MeasurementDTO> measurements; 

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MeasurementDTO {
        private Instant datetime;
        private Double temp;
        private Double humidity;
    }
}
