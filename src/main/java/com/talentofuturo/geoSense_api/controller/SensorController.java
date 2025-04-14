package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.controller.interfaces.ISensorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.service.SensorService;

import java.util.List;

/**
 * REST Controller implementation for sensor management operations.
 * Provides endpoints for creating and retrieving sensors.
 */
@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController implements ISensorController {

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

    @PutMapping("/{id}")
    public SensorDTO updateSensor(@PathVariable Long id, @RequestBody SensorDTO sensorDTO) {
        return sensorService.updateSensor(id, sensorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}
