package com.talentofuturo.geoSense_api.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;

public interface ILocationController {
    ResponseEntity<LocationDTO> createLocation(
            @RequestParam("company_api_key") String companyApiKey,
            @RequestBody Location location);

    ResponseEntity<LocationDTO> getLocation(@PathVariable Long locationId);

    ResponseEntity<Location> updateLocation(@PathVariable Long locationId, @RequestBody Location location);

    ResponseEntity<Void> deleteLocation(@PathVariable Long locationId);

    ResponseEntity<List<LocationDTO>> getAllLocations();
}