package com.talentofuturo.geoSense_api.controller.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.service.SensorDataService;

@RestController
@RequestMapping("/api/v1/sensor_data")
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<String> addSensorData(@RequestBody SensorDataDTO sensorDataDTO) {
        sensorDataService.processSensorData(sensorDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Datos guardados");
    }
}
