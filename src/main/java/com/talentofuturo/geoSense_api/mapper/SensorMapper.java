package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;

/**
 * Implementation of Sensor mapping operations.
 * Handles conversion between Sensor entities and DTOs.
 */
public class SensorMapper implements ISensorMapper {
    /**
     * {@inheritDoc}
     */
    public static SensorDTO mapSensor(Sensor sensor) {
        if (sensor == null) {
            return null;
        }

        return new SensorDTO(
                sensor.getId(),
                sensor.getSensorName(),
                sensor.getSensorCategory(),
                sensor.getSensorMeta()
        );
    }

    /**
     * {@inheritDoc}
     */
    public static Sensor mapDTO(SensorDTO sensorDTO) {
        if (sensorDTO == null) {
            return null;
        }

        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorDTO.getSensorName());
        sensor.setSensorCategory(sensorDTO.getSensorCategory());
        sensor.setSensorMeta(sensorDTO.getSensorMeta());
        return sensor;
    }
}
