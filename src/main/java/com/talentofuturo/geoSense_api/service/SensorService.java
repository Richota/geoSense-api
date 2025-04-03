package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.mapper.SensorMapper;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ISensorService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of sensor management operations.
 */
@Service
@AllArgsConstructor
public class SensorService implements ISensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;  // Add this field

    @Override
    public List<SensorDTO> getAllSensors() {
        return sensorRepository.findAll().stream()
                .map(sensorMapper::mapSensor)  // Use instance method
                .collect(Collectors.toList());
    }

    @Override
    public SensorDTO createSensor(SensorDTO sensorDTO) {
        Sensor sensor = sensorMapper.mapDTO(sensorDTO);  // Use instance method
        Sensor savedSensor = sensorRepository.save(sensor);
        return sensorMapper.mapSensor(savedSensor);  // Use instance method
    }
}
