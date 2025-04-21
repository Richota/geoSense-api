package com.talentofuturo.geoSense_api.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.talentofuturo.geoSense_api.dto.SensorDTO;
import com.talentofuturo.geoSense_api.entity.Sensor;

public interface ISensorController {
    ResponseEntity<Sensor> createSensor(@RequestParam String companyApiKey, @RequestParam Long locationId,
            @RequestBody Sensor sensor);

    ResponseEntity<Sensor> getSensor(@PathVariable Long sensorId);

    ResponseEntity<Sensor> updateSensor(@PathVariable Long sensorId, @RequestBody Sensor sensor);

    ResponseEntity<Void> deleteSensor(@PathVariable Long sensorId);

    ResponseEntity<List<SensorDTO>> getAllSensors();

    ResponseEntity<List<SensorDTO>> getSensorsByCompany(@RequestParam String companyApiKey);

    ResponseEntity<List<SensorDTO>> getSensorsByLocation(@PathVariable Long locationId);

    ResponseEntity<SensorDTO> getSensorByApiKey(@RequestParam String sensorApiKey);
}