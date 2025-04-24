package com.talentofuturo.geoSense_api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;

@Component
public class LocationMapper {

    public LocationDTO toDTO(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getLocationName(),
                location.getLocationCountry(),
                location.getLocationCity(),
                location.getLocationMeta(),
                location.getCompany().getCompanyApiKey() // Incluye solo el companyApiKey
        );
    }

    public List<LocationDTO> toDTOList(List<Location> locations) {
        return locations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}