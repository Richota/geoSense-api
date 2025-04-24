package com.talentofuturo.geoSense_api.mapper.interfaces;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;

public interface ISensorDataMapper {

    /**
     * Converts a SensorData entity to a SensorDataDTO.
     *
     * @param sensorData The SensorData entity to convert.
     * @return The corresponding SensorDataDTO.
     */
    SensorDataDTO toDTO(SensorData sensorData);

    /**
     * Converts a SensorDataDTO to a SensorData entity.
     *
     * @param dto The SensorDataDTO to convert.
     * @return The corresponding SensorData entity.
     */
    SensorData toEntity(SensorDataDTO dto);
}