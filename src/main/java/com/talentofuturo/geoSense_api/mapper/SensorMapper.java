package com.talentofuturo.geoSense_api.mapper;

import org.springframework.stereotype.Component;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.mapper.interfaces.ISensorMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of Sensor mapping operations.
 * Handles conversion between Sensor entities and DTOs.
 */
@Component
public class SensorMapper implements ISensorMapper {

    /**
     * {@inheritDoc}
     */
    public SensorDTO mapSensor(Sensor sensor) {
        if (sensor == null) {
            return null;
        }

        Long locationId = null;
        String locationName = null;
        if (sensor.getLocation() != null) {
            locationId = sensor.getLocation().getId();
            locationName = sensor.getLocation().getLocationName();
        }

        // Usar constructor para crear el DTO inmutable
        return new SensorDTO(
                sensor.getId(),
                sensor.getSensorName(),
                sensor.getSensorCategory(),
                sensor.getSensorMeta(),
                sensor.getSensorApiKey(),
                sensor.getCompanyApiKey(),
                locationId,
                locationName);
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
        sensor.setSensorMeta(sensorDTO.getSensorMeta());
        return sensor;
    }

    /**
     * Convierte una entidad Sensor a SensorDTO
     * 
     * @param sensor La entidad Sensor
     * @return SensorDTO con los datos relevantes
     */
    public SensorDTO toDTO(Sensor sensor) {
        if (sensor == null) {
            return null;
        }

        Long locationId = null;
        String locationName = null;
        if (sensor.getLocation() != null) {
            locationId = sensor.getLocation().getId();
            locationName = sensor.getLocation().getLocationName();
        }

        // Usar constructor para crear el DTO inmutable
        return new SensorDTO(
                sensor.getId(),
                sensor.getSensorName(),
                sensor.getSensorCategory(),
                sensor.getSensorMeta(),
                sensor.getSensorApiKey(),
                sensor.getCompanyApiKey(),
                locationId,
                locationName);
    }

    /**
     * Convierte una lista de entidades Sensor a lista de SensorDTO
     * 
     * @param sensors Lista de entidades Sensor
     * @return Lista de SensorDTO
     */
    public List<SensorDTO> toDTOList(List<Sensor> sensors) {
        return sensors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
