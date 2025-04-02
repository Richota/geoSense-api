package com.talentofuturo.geoSense_api.mapper;

import org.springframework.stereotype.Component;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.mapper.interfaces.ISensorMapper;

/**
 * Implementation of Sensor mapping operations.
 * Handles conversion between Sensor entities and DTOs.
 */
@Component
public class SensorMapper implements ISensorMapper {
    // Change methods from static to instance methods
    public SensorDTO mapSensor(Sensor sensor) {
        if (sensor == null) {
            return null;
        }

        return new SensorDTO(
        		sensor.getId(),
                sensor.getSensorName(),
                sensor.getSensorCategory(),
                sensor.getSensorStatus(),
                sensor.getSensorMeta(),
                sensor.getSensorApiKey()
        );
    }

    /**
     * {@inheritDoc}
     */
    public Sensor mapDTO(SensorDTO sensorDTO) {
        if (sensorDTO == null) {
            return null;
        }

        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorDTO.getSensorName());
        sensor.setSensorCategory(sensorDTO.getSensorCategory());
        sensor.setSensorStatus(sensorDTO.getSensorStatus());
        sensor.setSensorMeta(sensorDTO.getSensorMeta());
        return sensor;
    }
}
