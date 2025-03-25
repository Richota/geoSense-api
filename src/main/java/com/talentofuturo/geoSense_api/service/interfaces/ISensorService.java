/**
 * Interface defining operations for sensor management.
 */
public interface ISensorService {
    /**
     * Retrieves all sensors in the system.
     *
     * @return List of all sensors as DTOs
     */
    List<SensorDTO> getAllSensors();

    /**
     * Creates a new sensor.
     *
     * @param sensorDTO The sensor data to create
     * @return The created sensor as DTO
     */
    SensorDTO createSensor(SensorDTO sensorDTO);
}