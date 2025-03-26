package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object for Location information.
 * It transfers location data between layers of the application.
 */
@Getter
@AllArgsConstructor
public class LocationDTO {
    /**
     * Unique identifier for the location
     */
    private Long id;

    /**
     * Name of the location
     */
    private String locationName;

    /**
     * Country where the location is
     */
    private String locationCountry;

    /**
     * City where the location is
     */
    private String locationCity;

    /**
     * Additional metadata about the location
     */
    private String locationMeta;
}
