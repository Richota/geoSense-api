package com.talentofuturo.geoSense_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.mapper.LocationMapper;
import com.talentofuturo.geoSense_api.service.LocationService;
import com.talentofuturo.geoSense_api.controller.interfaces.ILocationController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/locations")
@AllArgsConstructor
public class LocationController implements ILocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation(
            @RequestParam("company_api_key") String companyApiKey,
            @RequestBody Location location) {
        Location createdLocation = locationService.createLocationByApiKey(companyApiKey, location);
        LocationDTO locationDTO = locationMapper.toDTO(createdLocation);
        return ResponseEntity.status(HttpStatus.CREATED).body(locationDTO);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long locationId) {
        Location location = locationService.getLocationById(locationId);
        LocationDTO locationDTO = locationMapper.toDTO(location);
        return ResponseEntity.ok(locationDTO);
    }

    @PutMapping("/update/{locationId}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long locationId, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(locationId, location);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        List<LocationDTO> locationDTOs = locationMapper.toDTOList(locations);
        return ResponseEntity.ok(locationDTOs);
    }
}