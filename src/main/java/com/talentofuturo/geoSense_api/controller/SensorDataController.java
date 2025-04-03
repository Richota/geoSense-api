package com.talentofuturo.geoSense_api.controller;

import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.talentofuturo.geoSense_api.controller.interfaces.ISensorDataController;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.service.SensorDataService;
import lombok.AllArgsConstructor;

/**
 * REST controller for managing sensor data operations.
 * Handles receiving sensor measurements and retrieving sensor data.
 */
@RestController
@RequestMapping("/api/v1/sensor-data")
@AllArgsConstructor
public class SensorDataController implements ISensorDataController {
    private final SensorDataService sensorDataService;

    @Override
    @PostMapping("/measurements")
    public ResponseEntity<String> receiveMeasurements(@RequestBody SensorDataDTO data) {
        sensorDataService.processSensorData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data saved successfully");
    }

    @Override
    @GetMapping("/sensors/{sensorId}")
    public ResponseEntity<List<SensorData>> getSensorMeasurements(
            @PathVariable Long sensorId,
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate) {
        return ResponseEntity.ok(sensorDataService.getMeasurements(sensorId, startDate, endDate));
    }

    @Override
    @GetMapping("/sensors/{sensorId}/latest")
    public ResponseEntity<SensorData> getLatestMeasurement(@PathVariable Long sensorId) {
        return ResponseEntity.ok(sensorDataService.getLatestMeasurement(sensorId));
    }
}