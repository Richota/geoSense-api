package com.talentofuturo.geoSense_api.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SensorDataDTO {
	@JsonProperty("api_key") // Mapea el campo del JSON
    private String sensorApiKey; 
	
	@JsonProperty("json_data") // Mapea el array del JSON
    private List<MeasurementDTO> measurements; 

    @Data
    public static class MeasurementDTO {
    	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT) // Para epoch seconds
        private Instant datetime;
        private Double temp;
        private Double humidity;
    }
}
