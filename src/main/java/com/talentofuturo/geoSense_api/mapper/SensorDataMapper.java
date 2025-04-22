package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;
import org.springframework.stereotype.Component;

@Component
public class SensorDataMapper {

    public SensorDataDTO toDTO(SensorData sensorData) {
        SensorDataDTO dto = new SensorDataDTO();
        dto.setId(sensorData.getId());
        dto.setApiKey(sensorData.getApiKey());
        dto.setTimestart(sensorData.getTimestart());
        dto.setTimeend(sensorData.getTimeend());
        dto.setMeasurementType1(sensorData.getMeasurementType1());
        dto.setMeasurementType2(sensorData.getMeasurementType2());
        dto.setMeasurementType3(sensorData.getMeasurementType3());
        dto.setSensorId(sensorData.getSensor().getId());
        return dto;
    }

    public SensorData toEntity(SensorDataDTO dto) {
        SensorData sensorData = new SensorData();
        sensorData.setId(dto.getId());
        sensorData.setApiKey(dto.getApiKey());
        sensorData.setTimestart(dto.getTimestart());
        sensorData.setTimeend(dto.getTimeend());
        sensorData.setMeasurementType1(dto.getMeasurementType1());
        sensorData.setMeasurementType2(dto.getMeasurementType2());
        sensorData.setMeasurementType3(dto.getMeasurementType3());
        // The sensor entity should be set elsewhere, as it requires fetching from the
        // database
        return sensorData;
    }
}