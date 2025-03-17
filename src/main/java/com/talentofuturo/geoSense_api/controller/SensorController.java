package com.talentofuturo.geoSense_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.service.SensorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @PostMapping
    public SensorDTO createSensor(@RequestBody SensorDTO sensorDTO) {
        return sensorService.createSensor(sensorDTO);
    }
}
