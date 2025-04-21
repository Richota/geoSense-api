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
    /**
     * Unique identifier for the location
     */
    private final Long id;

    /**
     * Name of the location
     */
    private final String locationName;

    /**
     * Country where the location is
     */
    private final String locationCountry;

    /**
     * City where the location is
     */
    private final String locationCity;

    /**
     * Additional metadata about the location
     */
    private final String locationMeta;

    /**
     * ID of the company associated with the location
     */
    private final Long companyId;

    /**
     * Name of the company associated with the location
     */
    private final String companyName;
}
