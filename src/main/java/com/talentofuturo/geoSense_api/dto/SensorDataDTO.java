package com.talentofuturo.geoSense_api.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SensorDataDTO {
    private String sensorApiKey; 
    private List<MeasurementDTO> measurements; 

    @Data
    public static class MeasurementDTO {
        private Instant datetime;
        private Double temp;
        private Double humidity;
    }
}
