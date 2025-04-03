package com.talentofuturo.geoSense_api.mapper.interfaces;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;

/**
 * Interface defining mapping operations between Sensor entities and DTOs.
 */
public interface ISensorMapper {
    /**
     * Converts a Sensor entity to a SensorDTO
     *
     * @param sensor The Sensor entity to convert
     * @return The corresponding SensorDTO, or null if input is null
     */
    SensorDTO mapSensor(Sensor sensor);

    /**
     * Converts a SensorDTO to a Sensor entity
     *
     * @param sensorDTO The SensorDTO to convert
     * @return The corresponding Sensor entity, or null if input is null
     */
    Sensor mapDTO(SensorDTO sensorDTO);
}