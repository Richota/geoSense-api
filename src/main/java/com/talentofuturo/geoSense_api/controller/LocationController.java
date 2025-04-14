package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.controller.interfaces.ILocationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.service.LocationService;

import java.util.List;

/**
 * REST Controller implementation for location management operations.
 * Provides endpoints for creating and retrieving locations.
 */
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController implements ILocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public LocationDTO createLocation(@RequestBody LocationDTO locationDTO) {
        return locationService.createLocation(locationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(
            @PathVariable Long id,
            @RequestBody LocationDTO locationDTO) {
        LocationDTO updatedLocation = locationService.updateLocation(id, locationDTO);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
