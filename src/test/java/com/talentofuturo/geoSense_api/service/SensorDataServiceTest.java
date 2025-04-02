package com.talentofuturo.geoSense_api.service;

import net.datafaker.Faker;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.exception.InvalidDataException;
import com.talentofuturo.geoSense_api.exception.InvalidSensorException;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SensorDataServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private SensorDataRepository sensorDataRepository;

    @InjectMocks
    private SensorDataService sensorDataService;

    private final Faker faker = new Faker();
    private SensorDataDTO.MeasurementDTO validMeasurement;
    private SensorDataDTO.MeasurementDTO invalidTempMeasurement;
    private SensorDataDTO.MeasurementDTO invalidHumidityMeasurement;

    @BeforeEach
    void setUp() {
        // Valid data
        validMeasurement = new SensorDataDTO.MeasurementDTO();
        validMeasurement.setDatetime(Instant.now());
        validMeasurement.setTemp(25.0);
        validMeasurement.setHumidity(50.0); 

        // Invalid Data. out of range
        invalidTempMeasurement = new SensorDataDTO.MeasurementDTO();
        invalidTempMeasurement.setDatetime(Instant.now());
        invalidTempMeasurement.setTemp(-50.0);
        invalidTempMeasurement.setHumidity(50.0);

        invalidHumidityMeasurement = new SensorDataDTO.MeasurementDTO();
        invalidHumidityMeasurement.setDatetime(Instant.now());
        invalidHumidityMeasurement.setTemp(25.0);
        invalidHumidityMeasurement.setHumidity(150.0);
    }

    @Test
    void whenValidApiKeyAndData_thenSaveSensorData() {
        String validApiKey = faker.internet().uuid();
        Sensor mockSensor = new Sensor();
        when(sensorRepository.findBySensorApiKey(validApiKey)).thenReturn(Optional.of(mockSensor));

        SensorDataDTO request = new SensorDataDTO();
        request.setSensorApiKey(validApiKey);
        
        SensorDataDTO.MeasurementDTO measurement = new SensorDataDTO.MeasurementDTO();
        measurement.setTemp(25.0);
        measurement.setHumidity(50.0);
        measurement.setDatetime(Instant.now());
        
        request.setMeasurements(List.of(measurement));

        sensorDataService.processSensorData(request);

        verify(sensorDataRepository, times(1)).saveAll(anyList());
    }

    @Test
    void whenInvalidApiKey_thenThrowInvalidSensorException() {
        String invalidApiKey = faker.internet().uuid();
        when(sensorRepository.findBySensorApiKey(invalidApiKey)).thenReturn(Optional.empty());

        SensorDataDTO request = new SensorDataDTO();
        request.setSensorApiKey(invalidApiKey);
        request.setMeasurements(List.of(validMeasurement));

        assertThrows(InvalidSensorException.class, () -> {
            sensorDataService.processSensorData(request);
        });
    }

    @Test
    void whenInvalidTemperature_thenThrowInvalidDataException() {
        String validApiKey = faker.internet().uuid();
        Sensor mockSensor = new Sensor();
        when(sensorRepository.findBySensorApiKey(validApiKey)).thenReturn(Optional.of(mockSensor));

        SensorDataDTO request = new SensorDataDTO();
        request.setSensorApiKey(validApiKey);
        request.setMeasurements(List.of(invalidTempMeasurement));

        assertThrows(InvalidDataException.class, () -> {
            sensorDataService.processSensorData(request);
        });
    }

    @Test
    void whenInvalidHumidity_thenThrowInvalidDataException() {
        String validApiKey = faker.internet().uuid();
        Sensor mockSensor = new Sensor();
        when(sensorRepository.findBySensorApiKey(validApiKey)).thenReturn(Optional.of(mockSensor));

        SensorDataDTO request = new SensorDataDTO();
        request.setSensorApiKey(validApiKey);
        request.setMeasurements(List.of(invalidHumidityMeasurement));

        assertThrows(InvalidDataException.class, () -> {
            sensorDataService.processSensorData(request);
        });
    }

    @Test
    void whenGetMeasurementsWithDateRange_thenReturnFilteredData() {
        Long sensorId = 1L;
        Instant startDate = Instant.now().minusSeconds(3600);
        Instant endDate = Instant.now();
        List<SensorData> mockData = List.of(new SensorData(), new SensorData());

        when(sensorDataRepository.findBySensorIdAndDatetimeBetween(sensorId, startDate, endDate))
            .thenReturn(mockData);

        List<SensorData> result = sensorDataService.getMeasurements(sensorId, startDate, endDate);

        assertEquals(2, result.size());
        verify(sensorDataRepository, times(1))
            .findBySensorIdAndDatetimeBetween(sensorId, startDate, endDate);
    }

    @Test
    void whenGetLatestMeasurement_thenReturnMostRecent() {
        Long sensorId = 1L;
        SensorData mockData = new SensorData();
        when(sensorDataRepository.findFirstBySensorIdOrderByDatetimeDesc(sensorId))
            .thenReturn(Optional.of(mockData));

        SensorData result = sensorDataService.getLatestMeasurement(sensorId);

        assertNotNull(result);
        verify(sensorDataRepository, times(1))
            .findFirstBySensorIdOrderByDatetimeDesc(sensorId);
    }
}
