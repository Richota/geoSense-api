package com.talentofuturo.geoSense_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.mapper.SensorMapper;
import com.talentofuturo.geoSense_api.repository.SensorRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of sensor management operations.
 */
@Service
@AllArgsConstructor
public class SensorService implements ISensorService {
    private final SensorRepository sensorRepository;

    @Override
    public List<SensorDTO> getAllSensors() {
        return sensorRepository.findAll().stream()
                .map(SensorMapper::mapSensor)
                .collect(Collectors.toList());
    }

    @Override
    public SensorDTO createSensor(SensorDTO sensorDTO) {
        Sensor sensor = SensorMapper.mapDTO(sensorDTO);
        Sensor savedSensor = sensorRepository.save(sensor);
        return SensorMapper.mapSensor(savedSensor);
    }
}
