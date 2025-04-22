package com.talentofuturo.geoSense_api.service;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.mapper.SensorDataMapper;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.service.interfaces.ISensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorDataService implements ISensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final SensorRepository sensorRepository;
    private final SensorDataMapper sensorDataMapper;

    /**
     * Save sensor data using sensor_api_key for authorization.
     *
     * @param sensorApiKey  The API key of the sensor.
     * @param sensorDataDTO The sensor data to save.
     * @return The saved sensor data.
     */
    public SensorDataDTO saveSensorData(String sensorApiKey, SensorDataDTO sensorDataDTO) {
        // Cambiamos findByApiKey a findBySensorApiKey
        Sensor sensor = sensorRepository.findBySensorApiKey(sensorApiKey)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with API key: " + sensorApiKey));

        SensorData sensorData = sensorDataMapper.toEntity(sensorDataDTO);
        sensorData.setSensor(sensor);

        SensorData savedSensorData = sensorDataRepository.save(sensorData);
        return sensorDataMapper.toDTO(savedSensorData);
    }

    /**
     * Query sensor data with filters.
     *
     * @param companyApiKey The API key of the company.
     * @param from          The start timestamp.
     * @param to            The end timestamp.
     * @param sensorIds     The list of sensor IDs to query.
     * @return A list of sensor data matching the filters.
     */
    @Override
    public List<SensorDataDTO> querySensorData(String companyApiKey, Instant from, Instant to, List<Long> sensorIds) {
        // Corrected method call
        List<SensorData> sensorDataList = sensorDataRepository.findBySensorIdsAndTimestartBetween(sensorIds, from, to);
        return sensorDataList.stream()
                .map(sensorDataMapper::toDTO)
                .collect(Collectors.toList());
    }
}