/**
 * Interface defining operations for location management.
 */
public interface ILocationService {
    /**
     * Retrieves all locations in the system.
     *
     * @return List of all locations as DTOs
     */
    List<LocationDTO> getAllLocations();

    /**
     * Creates a new location.
     *
     * @param locationDTO The location data to create
     * @return The created location as DTO
     */
    LocationDTO createLocation(LocationDTO locationDTO);
}