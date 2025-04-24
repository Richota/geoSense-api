package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SensorMapper {

    // Convierte una entidad Sensor a un DTO SensorDTO
    public SensorDTO toDTO(Sensor sensor) {
        return new SensorDTO(
                sensor.getId(),
                sensor.getSensorName(),
                sensor.getSensorCategory(),
                sensor.getSensorMeta(),
                sensor.getSensorApiKey(),
                sensor.getCompanyApiKey(),
                sensor.getLocation() != null ? sensor.getLocation().getId() : null // Maneja el caso de ubicación nula
        );
    }

    // Convierte un DTO SensorDTO a una entidad Sensor
    public Sensor toEntity(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setId(sensorDTO.getId());
        sensor.setSensorName(sensorDTO.getSensorName());
        sensor.setSensorCategory(sensorDTO.getSensorCategory());
        sensor.setSensorMeta(sensorDTO.getSensorMeta());
        sensor.setSensorApiKey(sensorDTO.getSensorApiKey());
        sensor.setCompanyApiKey(sensorDTO.getCompanyApiKey());
        // La ubicación se asignará en el servicio, no aquí
        return sensor;
    }

    // Convierte una lista de entidades Sensor a una lista de DTOs SensorDTO
    public List<SensorDTO> toDTOList(List<Sensor> sensors) {
        return sensors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}