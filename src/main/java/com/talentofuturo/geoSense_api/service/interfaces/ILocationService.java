package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.entity.Location;

/**
 * Interface defining operations for managing locations.
 */
public interface ILocationService {

    /**
     * Retrieves a location by its ID.
     *
     * @param locationId The ID of the location.
     * @return The location entity.
     */
    Location getLocationById(Long locationId);

    /**
     * Creates a new location associated with a company.
     *
     * @param companyId The ID of the company.
     * @param location  The location data to create.
     * @return The created location entity.
     */
    Location createLocation(Long companyId, Location location);

    /**
     * Updates an existing location.
     *
     * @param locationId The ID of the location to update.
     * @param location   The updated location data.
     * @return The updated location entity.
     */
    Location updateLocation(Long locationId, Location location);

    /**
     * Deletes a location by its ID.
     *
     * @param locationId The ID of the location to delete.
     */
    void deleteLocation(Long locationId);

    /**
     * Retrieves all locations associated with a specific company.
     *
     * @param companyId The ID of the company.
     * @return A list of locations.
     */
    List<Location> getAllLocations();
}