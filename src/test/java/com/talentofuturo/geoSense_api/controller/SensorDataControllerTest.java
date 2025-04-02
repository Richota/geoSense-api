package com.talentofuturo.geoSense_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.service.SensorDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SensorDataControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Mock
    private SensorDataService sensorDataService;

    @InjectMocks
    private SensorDataController sensorDataController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sensorDataController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    void receiveMeasurements_ShouldReturn201() throws Exception {
        SensorDataDTO.MeasurementDTO measurement = new SensorDataDTO.MeasurementDTO();
        measurement.setDatetime(Instant.parse("2023-10-01T12:00:00Z"));
        measurement.setTemp(25.0);
        measurement.setHumidity(50.0);

        SensorDataDTO request = new SensorDataDTO();
        request.setSensorApiKey("valid-api-key");
        request.setMeasurements(List.of(measurement));

        doNothing().when(sensorDataService).processSensorData(any(SensorDataDTO.class));

        mockMvc.perform(post("/api/v1/sensor-data/measurements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void getSensorMeasurements_WithDates_ShouldReturn200() throws Exception {
        // Given
        Long sensorId = 1L;
        Instant startDate = Instant.parse("2023-01-01T00:00:00Z");
        Instant endDate = Instant.parse("2023-01-02T00:00:00Z");
        SensorData sensorData = new SensorData();
        sensorData.setTemp(25.0);

        when(sensorDataService.getMeasurements(sensorId, startDate, endDate))
                .thenReturn(List.of(sensorData));

        // When/Then
        mockMvc.perform(get("/api/v1/sensor-data/sensors/{sensorId}", sensorId)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].temp").value(25.0));

        verify(sensorDataService, times(1)).getMeasurements(sensorId, startDate, endDate);
    }

    @Test
    void getSensorMeasurements_WithoutDates_ShouldReturn200() throws Exception {
        // Given
        Long sensorId = 1L;
        SensorData sensorData = new SensorData();
        sensorData.setTemp(22.0);

        when(sensorDataService.getMeasurements(sensorId, null, null))
                .thenReturn(List.of(sensorData));

        // When/Then
        mockMvc.perform(get("/api/v1/sensor-data/sensors/{sensorId}", sensorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].temp").value(22.0));

        verify(sensorDataService, times(1)).getMeasurements(sensorId, null, null);
    }

    @Test
    void getLatestMeasurement_ShouldReturn200() throws Exception {
        // Given
        Long sensorId = 1L;
        SensorData sensorData = new SensorData();
        sensorData.setTemp(28.0);

        when(sensorDataService.getLatestMeasurement(sensorId))
                .thenReturn(sensorData);

        // When/Then
        mockMvc.perform(get("/api/v1/sensor-data/sensors/{sensorId}/latest", sensorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temp").value(28.0));

        verify(sensorDataService, times(1)).getLatestMeasurement(sensorId);
    }
}
