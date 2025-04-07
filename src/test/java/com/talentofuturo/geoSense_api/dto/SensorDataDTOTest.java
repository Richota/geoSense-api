package com.talentofuturo.geoSense_api.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SensorDataDTOTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testJsonSerialization() throws JsonProcessingException {
        SensorDataDTO.MeasurementDTO measurement = new SensorDataDTO.MeasurementDTO();
        measurement.setDatetime(Instant.ofEpochSecond(1742860430));
        measurement.setTemp(24.4);
        measurement.setHumidity(0.5);

        SensorDataDTO dto = new SensorDataDTO();
        dto.setSensorApiKey("bcc21b71-d70f-4a18-afee-63416db16af8");
        dto.setMeasurements(List.of(measurement));

        String json = objectMapper.writeValueAsString(dto);

        String expectedJson = """
            {
                "api_key":"bcc21b71-d70f-4a18-afee-63416db16af8",
                "json_data":[
                    {
                        "datetime":1742860430000,
                        "temp":24.4,
                        "humidity":0.5
                    }
                ]
            }""".replaceAll("\\s", "");

        assertEquals(expectedJson, json.replaceAll("\\s", ""));
    }

    @Test
    void testJsonDeserialization() throws JsonProcessingException {
        String json = """
            {
                "api_key":"bcc21b71-d70f-4a18-afee-63416db16af8",
                "json_data":[
                    {
                        "datetime":1742860430,
                        "temp":24.4,
                        "humidity":0.5
                    }
                ]
            }""";

        SensorDataDTO dto = objectMapper.readValue(json, SensorDataDTO.class);

        assertEquals("bcc21b71-d70f-4a18-afee-63416db16af8", dto.getSensorApiKey());
        assertEquals(1, dto.getMeasurements().size());
        
        SensorDataDTO.MeasurementDTO measurement = dto.getMeasurements().get(0);
        assertEquals(Instant.ofEpochSecond(1742860430), measurement.getDatetime());
        assertEquals(24.4, measurement.getTemp());
        assertEquals(0.5, measurement.getHumidity());
    }

    @Test
    void testMeasurementDTOValidation() {
        SensorDataDTO.MeasurementDTO measurement = new SensorDataDTO.MeasurementDTO();

        assertAll(
            () -> {
                measurement.setDatetime(null);
                assertNull(measurement.getDatetime());
            },
            () -> {
                measurement.setTemp(-40.0);
                assertEquals(-40.0, measurement.getTemp());
            },
            () -> {
                measurement.setHumidity(100.0);
                assertEquals(100.0, measurement.getHumidity());
            }
        );
    }

    @Test
    void testSensorDataDTOEdgeCases() {
        // Arrange
        SensorDataDTO dto = new SensorDataDTO();

        // Act & Assert
        assertAll(
            () -> {
                dto.setSensorApiKey(null);
                assertNull(dto.getSensorApiKey());
            },
            () -> {
                dto.setMeasurements(List.of());
                assertTrue(dto.getMeasurements().isEmpty());
            },
            () -> {
                dto.setMeasurements(null);
                assertNull(dto.getMeasurements());
            }
        );
    }

    @Test
    void testLombokAnnotations() {
        SensorDataDTO dto = new SensorDataDTO();
        dto.setSensorApiKey("test");
        assertNotNull(dto.toString());
        assertNotEquals(0, dto.hashCode());
    }
}
