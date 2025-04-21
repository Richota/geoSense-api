package com.talentofuturo.geoSense_api.mapper;

import org.springframework.stereotype.Component;

import com.talentofuturo.geoSense_api.dto.LocationDTO;
import com.talentofuturo.geoSense_api.entity.Location;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    /**
     * Convierte una entidad Location a LocationDTO
     * 
     * @param location La entidad Location
     * @return LocationDTO con los datos relevantes
     */
    public LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }

        Long companyId = null;
        String companyName = null;
        if (location.getCompany() != null) {
            companyId = location.getCompany().getId();
            companyName = location.getCompany().getCompanyName();
        }

        // Usar constructor para crear el DTO inmutable
        return new LocationDTO(
                location.getId(),
                location.getLocationName(),
                location.getLocationCountry(),
                location.getLocationCity(),
                location.getLocationMeta(),
                companyId,
                companyName);
    }

    /**
     * Convierte una lista de entidades Location a lista de LocationDTO
     * 
     * @param locations Lista de entidades Location
     * @return Lista de LocationDTO
     */
    public List<LocationDTO> toDTOList(List<Location> locations) {
        return locations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
