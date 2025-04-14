package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.mapper.SensorMapper;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ISensorService;

import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public SensorDTO updateSensor(Long id, SensorDTO sensorDTO) {
        return sensorRepository.findById(id)
                .map(existingSensor -> {
                    // Solo actualiza campos permitidos
                    existingSensor.setSensorName(sensorDTO.getSensorName());
                    existingSensor.setSensorCategory(sensorDTO.getSensorCategory());
                    existingSensor.setSensorStatus(sensorDTO.getSensorStatus());
                    existingSensor.setSensorLatitude(sensorDTO.getSensorLatitude());
                    existingSensor.setSensorLongitude(sensorDTO.getSensorLongitude());
                    existingSensor.setSensorMeta(sensorDTO.getSensorMeta());
                    
                    // Campos que NO se actualizan:
                    // sensorApiKey (se mantiene el original)
                    // location (se maneja por separado si es necesario)
                    
                    return sensorMapper.mapSensor(sensorRepository.save(existingSensor));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Sensor", "id", id));
    }

    @Override
    @Transactional
    public void deleteSensor(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sensor", "id", id);
        }
        sensorRepository.deleteById(id);
    }
}
