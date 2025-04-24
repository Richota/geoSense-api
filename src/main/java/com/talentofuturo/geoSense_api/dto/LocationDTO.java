package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object for Location information.
 * It transfers location data between layers of the application.
 * Es inmutable para garantizar la integridad de los datos.
 */
@Getter
@AllArgsConstructor
public class LocationDTO {
    private final Long id;
    private final String locationName;
    private final String locationCountry;
    private final String locationCity;
    private final String locationMeta;
    private final String companyApiKey; // Incluye solo el companyApiKey
}