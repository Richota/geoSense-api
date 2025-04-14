package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.mapper.LocationMapper;
import com.talentofuturo.geoSense_api.repository.LocationRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ILocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Implementation of location management operations.
 */
@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationMapper::mapLocation)
                .toList();
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = LocationMapper.mapDTO(locationDTO);
        Location savedLocation = locationRepository.save(location);
        return LocationMapper.mapLocation(savedLocation);
    }

    @Override
    @Transactional
    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location", "id", id));

        existingLocation.setLocationName(locationDTO.getLocationName());
        existingLocation.setLocationCountry(locationDTO.getLocationCountry());
        existingLocation.setLocationCity(locationDTO.getLocationCity());
        existingLocation.setLocationMeta(locationDTO.getLocationMeta());

        return LocationMapper.mapLocation(locationRepository.save(existingLocation));
    }

    @Override
    @Transactional
    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Location", "id", id);
        }
        locationRepository.deleteById(id);
    }
}
