package com.talentofuturo.geoSense_api.service;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.mapper.SensorDataMapper;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final SensorRepository sensorRepository;
    private final SensorDataMapper sensorDataMapper;

    public SensorDataDTO saveSensorData(String sensorApiKey, SensorDataDTO sensorDataDTO) {
        Sensor sensor = sensorRepository.findBySensorApiKey(sensorApiKey)
                .orElseThrow(() -> new RuntimeException("Sensor not found with API key: " + sensorApiKey));

        SensorData sensorData = sensorDataMapper.toEntity(sensorDataDTO);
        sensorData.setSensor(sensor);

        SensorData savedSensorData = sensorDataRepository.save(sensorData);
        return sensorDataMapper.toDTO(savedSensorData);
    }

    public List<SensorDataDTO> querySensorData(String companyApiKey, Instant from, Instant to, List<Long> sensorIds) {
        List<SensorData> sensorDataList = sensorDataRepository.findBySensorIdInAndTimestartBetween(sensorIds, from, to);
        return sensorDataList.stream()
                .map(sensorDataMapper::toDTO)
                .collect(Collectors.toList());
    }
}