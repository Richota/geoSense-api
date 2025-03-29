package com.talentofuturo.geoSense_api.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.exception.InvalidSensorException;
import com.talentofuturo.geoSense_api.exception.InvalidDataException;

@Service
public class SensorDataService {
    private static final Logger logger = LoggerFactory.getLogger(SensorDataService.class);
    private static final double MIN_TEMP = -40.0;
    private static final double MAX_TEMP = 85.0;
    private static final double MIN_HUMIDITY = 0.0;
    private static final double MAX_HUMIDITY = 100.0;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Transactional
    public void processSensorData(SensorDataDTO sensorDataDTO) {
        logger.info("Processing sensor data for API key: {}", sensorDataDTO.getSensorApiKey());
        
        Sensor sensor = sensorRepository.findBySensorApiKey(sensorDataDTO.getSensorApiKey())
            .orElseThrow(() -> new InvalidSensorException("Invalid sensor API key: " + sensorDataDTO.getSensorApiKey()));

        List<SensorData> sensorDataList = sensorDataDTO.getMeasurements().stream()
            .map(measurement -> {
                validateMeasurement(measurement);
                SensorData data = new SensorData();
                data.setSensor(sensor);
                data.setDatetime(measurement.getDatetime());
                data.setTemp(measurement.getTemp());
                data.setHumidity(measurement.getHumidity());
                return data;
            })
            .toList();

        try {
            sensorDataRepository.saveAll(sensorDataList);
            logger.info("Successfully saved {} measurements for sensor {}", 
                sensorDataList.size(), sensor.getId());
        } catch (Exception e) {
            logger.error("Error saving sensor data: {}", e.getMessage());
            throw new RuntimeException("Failed to save sensor data", e);
        }
    }

    private void validateMeasurement(SensorDataDTO.MeasurementDTO measurement) {
        if (measurement.getTemp() < MIN_TEMP || measurement.getTemp() > MAX_TEMP) {
            throw new InvalidDataException("Temperature out of valid range: " + measurement.getTemp());
        }
        if (measurement.getHumidity() < MIN_HUMIDITY || measurement.getHumidity() > MAX_HUMIDITY) {
            throw new InvalidDataException("Humidity out of valid range: " + measurement.getHumidity());
        }
    }
}
