package com.talentofuturo.geoSense_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.service.LocationService;
import com.talentofuturo.geoSense_api.controller.interfaces.ILocationController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/locations")
@AllArgsConstructor
public class LocationController implements ILocationController {

    private final LocationService locationService;

    @PostMapping("/create/{companyId}")
    public ResponseEntity<Location> createLocation(@PathVariable Long companyId, @RequestBody Location location) {
        Location createdLocation = locationService.createLocation(companyId, location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocation(@PathVariable Long locationId) {
        Location location = locationService.getLocationById(locationId);
        return ResponseEntity.ok(location);
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
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
}