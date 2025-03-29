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
    private String api_key; // sensor_api_key para validar el sensor
    private List<MeasurementDTO> json_data; // Lista de mediciones

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MeasurementDTO {
        private Instant datetime;
        private Double temp;
        private Double humidity;
    }
}
