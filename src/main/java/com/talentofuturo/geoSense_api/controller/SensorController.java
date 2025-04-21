package com.talentofuturo.geoSense_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.mapper.SensorMapper;
import com.talentofuturo.geoSense_api.service.SensorService;
import com.talentofuturo.geoSense_api.controller.interfaces.ISensorController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/sensors")
@AllArgsConstructor
public class SensorController implements ISensorController {

    private final SensorService sensorService;
    private final SensorMapper sensorMapper;

    @PostMapping("/create")
    public ResponseEntity<Sensor> createSensor(@RequestParam String companyApiKey, @RequestParam Long locationId,
            @RequestBody Sensor sensor) {
        Sensor createdSensor = sensorService.createSensor(companyApiKey, locationId, sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<Sensor> getSensor(@PathVariable Long sensorId) {
        Sensor sensor = sensorService.getSensorById(sensorId);
        return ResponseEntity.ok(sensor);
    }

    @PutMapping("/update/{sensorId}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Long sensorId, @RequestBody Sensor sensor) {
        Sensor updatedSensor = sensorService.updateSensor(sensorId, sensor);
        return ResponseEntity.ok(updatedSensor);
    }

    @DeleteMapping("/delete/{sensorId}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long sensorId) {
        sensorService.deleteSensor(sensorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
        List<SensorDTO> sensorDTOs = sensorMapper.toDTOList(sensors);
        return ResponseEntity.ok(sensorDTOs);
    }

    @GetMapping("/by-company")
    public ResponseEntity<List<SensorDTO>> getSensorsByCompany(@RequestParam String companyApiKey) {
        List<Sensor> sensors = sensorService.getSensorsByCompany(companyApiKey);
        List<SensorDTO> sensorDTOs = sensorMapper.toDTOList(sensors);
        return ResponseEntity.ok(sensorDTOs);
    }

    @GetMapping("/by-location/{locationId}")
    public ResponseEntity<List<SensorDTO>> getSensorsByLocation(@PathVariable Long locationId) {
        List<Sensor> sensors = sensorService.getSensorsByLocation(locationId);
        List<SensorDTO> sensorDTOs = sensorMapper.toDTOList(sensors);
        return ResponseEntity.ok(sensorDTOs);
    }

    @GetMapping("/api-key")
    public ResponseEntity<SensorDTO> getSensorByApiKey(@RequestParam String sensorApiKey) {
        Sensor sensor = sensorService.getSensorByApiKey(sensorApiKey);
        SensorDTO sensorDTO = sensorMapper.toDTO(sensor);
        return ResponseEntity.ok(sensorDTO);
    }
}