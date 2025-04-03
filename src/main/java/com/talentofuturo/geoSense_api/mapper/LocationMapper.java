package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;

public class LocationMapper {

    public static LocationDTO mapLocation(Location location) {
        if (location == null) {
            return null;
        }

        return new LocationDTO(
        		location.getId(),
        		location.getLocationName(),
        		location.getLocationCountry(),
        		location.getLocationCity(),
        		location.getLocationMeta()
        		);
    }

    public static Location mapDTO(LocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }

        Location location = new Location();
        location.setLocationName(locationDTO.getLocationName());
        location.setLocationCountry(locationDTO.getLocationCountry());
        location.setLocationCity(locationDTO.getLocationCity());
        location.setLocationMeta(locationDTO.getLocationMeta());
        return location;
    }
}
