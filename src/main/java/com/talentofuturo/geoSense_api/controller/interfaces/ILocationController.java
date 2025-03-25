/**
 * Defines the contract for location management operations through REST endpoints.
 * Handles basic CRUD operations for locations in the system.
 */
public interface ILocationController {
    /**
     * Retrieves a list of all locations in the system.
     * Accessible via GET /api/v1/locations
     *
     * @return A list of locations represented as DTOs
     */
    List<LocationDTO> getAllLocations();

    /**
     * Creates a new location in the system.
     * Accessible via POST /api/v1/locations
     *
     * @param locationDTO The location information to be created
     * @return The created location as a DTO with generated ID
     */
    LocationDTO createLocation(LocationDTO locationDTO);
}