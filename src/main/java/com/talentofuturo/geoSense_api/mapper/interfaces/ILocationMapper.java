/**
 * Interface defining mapping operations between Location entities and DTOs.
 */
public interface ILocationMapper {
    /**
     * Converts a Location entity to a LocationDTO
     *
     * @param location The Location entity to convert
     * @return The corresponding LocationDTO, or null if input is null
     */
    LocationDTO mapLocation(Location location);

    /**
     * Converts a LocationDTO to a Location entity
     *
     * @param locationDTO The LocationDTO to convert
     * @return The corresponding Location entity, or null if input is null
     */
    Location mapDTO(LocationDTO locationDTO);
}