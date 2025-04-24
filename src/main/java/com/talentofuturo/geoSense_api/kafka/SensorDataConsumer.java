package com.talentofuturo.geoSense_api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentofuturo.geoSense_api.dto.SensorDataMessage;
import com.talentofuturo.geoSense_api.entity.Sensor;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.repository.SensorDataRepository;
import com.talentofuturo.geoSense_api.repository.SensorRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataConsumer.class);

    private final ObjectMapper objectMapper;
    private final SensorRepository sensorRepository;
    private final SensorDataRepository sensorDataRepository;

    // In-memory storage for consumed messages
    private final List<SensorDataMessage> consumedMessages = new ArrayList<>();

    // Constructor para inyectar dependencias
    public SensorDataConsumer(
            ObjectMapper objectMapper,
            SensorRepository sensorRepository,
            SensorDataRepository sensorDataRepository) {
        this.objectMapper = objectMapper;
        this.sensorRepository = sensorRepository;
        this.sensorDataRepository = sensorDataRepository;
    }

    /**
     * Consumes sensor data from the Kafka topic, validates API key and stores data
     * in the database.
     *
     * @param message The JSON message received from Kafka.
     */
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeSensorData(String message) {
        try {
            logger.info("Received sensor data: {}", message);

            // Parse the JSON message into a SensorDataMessage object
            SensorDataMessage sensorDataMessage = objectMapper.readValue(message,
                    SensorDataMessage.class);

            // Validate API key against sensor_api_key column in the sensors table
            String apiKey = sensorDataMessage.getApiKey();
            Optional<Sensor> sensorOptional = sensorRepository.findBySensorApiKey(apiKey);

            if (sensorOptional.isEmpty()) {
                logger.warn("Received data with invalid API key: {}", apiKey);
                return; // Skip processing for invalid API keys
            }

            Sensor sensor = sensorOptional.get();

            // Process the data if the API key is valid
            if (sensorDataMessage.getJsonData() != null && !sensorDataMessage.getJsonData().isEmpty()) {
                // Save the message to in-memory storage for reference
                synchronized (consumedMessages) {
                    consumedMessages.add(sensorDataMessage);
                }

                // Process each reading in the message
                SensorDataMessage.SensorReading reading = sensorDataMessage.getJsonData().get(0);

                // Convert epoch seconds to Instant
                Instant timestamp = Instant.ofEpochSecond(reading.getDatetime());

                try {

                    saveSensorReading(sensor, "temp", reading.getTemp().doubleValue(), timestamp);
                    saveSensorReading(sensor, "humidity", reading.getHumidity().doubleValue(), timestamp);

                    logger.info(
                            "Processed data - Sensor ID: {}, API Key: {}, Temperature: {}°C, Humidity: {}%, Timestamp: {}",
                            sensor.getId(),
                            apiKey,
                            reading.getTemp(),
                            reading.getHumidity(),
                            timestamp);
                } catch (DataIntegrityViolationException e) {
                    logger.error("Database integrity error when saving sensor data: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error("Error processing sensor data message: {}", message, e);
        }
    }

    /**
     * Helper method to save a sensor reading to the database.
     */
    @Transactional
    private void saveSensorReading(Sensor sensor, String measurementType, Double value, Instant timestamp) {
        if (value != null) {
            SensorData sensorData = new SensorData();
            sensorData.setSensor(sensor);
            sensorData.setMeasurementType(measurementType);
            sensorData.setApiKey(value);
            sensorData.setTimestamp(timestamp);

            sensorDataRepository.save(sensorData);
            logger.debug("Saved {} reading: {} for sensor: {}", measurementType, value, sensor.getId());
        }
    }

    /**
     * Returns the list of consumed messages.
     *
     * @return List of SensorDataMessage objects.
     */
    public List<SensorDataMessage> getConsumedMessages() {
        synchronized (consumedMessages) {
            return new ArrayList<>(consumedMessages);
        }
    }
}