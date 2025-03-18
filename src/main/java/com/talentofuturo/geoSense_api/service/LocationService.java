package com.talentofuturo.geoSense_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.mapper.LocationMapper;
import com.talentofuturo.geoSense_api.repository.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationMapper::mapLocation)
                .collect(Collectors.toList());
    }

    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = LocationMapper.mapDTO(locationDTO);
        Location savedLocation = locationRepository.save(location);
        return LocationMapper.mapLocation(savedLocation);
    }
}
