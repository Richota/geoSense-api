package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;

public class SensorMapper {

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
