package com.talentofuturo.geoSense_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;

@Service
public class SensorDataService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public void processSensorData(SensorDataDTO sensorDataDTO) {
        // 1. Validar el sensor_api_key
        Sensor sensor = sensorRepository.findBySensorApiKey(sensorDataDTO.getSensorApiKey());
           // .orElseThrow(() -> new RuntimeException("Sensor no válido"));

        // 2. Convertir cada medición a entidad SensorData
        List<SensorData> sensorDataList = sensorDataDTO.getJson_data().stream()
            .map(measurement -> {
                SensorData data = new SensorData();
                data.setSensor(sensor);
                data.setDatetime(measurement.getDatetime());
                data.setTemp(measurement.getTemp());
                data.setHumidity(measurement.getHumidity());
                return data;
            }).toList();

        // 3. Guardar todas las mediciones
        sensorDataRepository.saveAll(sensorDataList);
    }
}
