package com.talentofuturo.geoSense_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.repository.LocationRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ISensorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorService implements ISensorService {

    private final SensorRepository sensorRepository;
    private final LocationRepository locationRepository;

    @Override
    public Sensor getSensorById(Long sensorId) {
        return sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor with ID " + sensorId + " not found"));
    }

    @Override
    public Sensor createSensor(String companyApiKey, Long locationId, Sensor sensor) {
        Location location = locationRepository.findById(locationId)
                .filter(loc -> loc.getCompany().getCompanyApiKey().equals(companyApiKey))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Location not found or does not belong to the company"));

        sensor.setLocation(location);
        sensor.setCompanyApiKey(companyApiKey);
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateSensor(Long sensorId, Sensor sensorDetails) {
        Sensor sensor = getSensorById(sensorId);
        sensor.setSensorName(sensorDetails.getSensorName());
        sensor.setSensorCategory(sensorDetails.getSensorCategory());
        sensor.setSensorMeta(sensorDetails.getSensorMeta());
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Long sensorId) {
        if (!sensorRepository.existsById(sensorId)) {
            throw new ResourceNotFoundException("Sensor with ID " + sensorId + " not found");
        }
        sensorRepository.deleteById(sensorId);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}