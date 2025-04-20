package com.talentofuturo.geoSense_api.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a sensor data message to be sent to Kafka.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDataMessage {
    @JsonProperty("api_key")
    private String apiKey;

    @JsonProperty("json_data")
    private List<SensorReading> jsonData;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SensorReading {
        private Long datetime;
        private Integer temp;
        private Integer humidity;
    }
}