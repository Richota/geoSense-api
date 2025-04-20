package com.talentofuturo.geoSense_api.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.talentofuturo.geoSense_api.entity.Location;

public interface ILocationController {
    ResponseEntity<Location> createLocation(@PathVariable Long companyId, @RequestBody Location location);

    ResponseEntity<Location> getLocation(@PathVariable Long locationId);

    ResponseEntity<Location> updateLocation(@PathVariable Long locationId, @RequestBody Location location);

    ResponseEntity<Void> deleteLocation(@PathVariable Long locationId);

    ResponseEntity<List<Location>> getAllLocations();
}