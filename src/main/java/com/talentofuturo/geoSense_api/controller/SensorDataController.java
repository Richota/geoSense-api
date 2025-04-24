package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sensor_data")
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<SensorDataDTO> insertSensorData(
            @RequestHeader("sensor_api_key") String sensorApiKey,
            @RequestBody SensorDataDTO sensorDataDTO) {
        SensorDataDTO savedData = sensorDataService.saveSensorData(sensorApiKey, sensorDataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @GetMapping
    public ResponseEntity<List<SensorDataDTO>> querySensorData(
            @RequestParam("company_api_key") String companyApiKey,
            @RequestParam("from") long from,
            @RequestParam("to") long to,
            @RequestParam("sensor_id") List<Long> sensorIds) {
        Instant fromTimestamp = Instant.ofEpochSecond(from);
        Instant toTimestamp = Instant.ofEpochSecond(to);
        List<SensorDataDTO> sensorDataList = sensorDataService.querySensorData(companyApiKey, fromTimestamp,
                toTimestamp, sensorIds);
        return ResponseEntity.ok(sensorDataList);
    }
}