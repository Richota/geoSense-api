/**
 * Defines the contract for sensor management operations through REST endpoints.
 * Handles basic CRUD operations for sensors in the system.
 */
public interface ISensorController {
    /**
     * Retrieves a list of all sensors in the system.
     * Accessible via GET /api/v1/sensors
     *
     * @return A list of sensors represented as DTOs
     */
    List<SensorDTO> getAllSensors();

    /**
     * Creates a new sensor in the system.
     * Accessible via POST /api/v1/sensors
     *
     * @param sensorDTO The sensor information to be created
     * @return The created sensor as a DTO with generated ID
     */
    SensorDTO createSensor(SensorDTO sensorDTO);
}