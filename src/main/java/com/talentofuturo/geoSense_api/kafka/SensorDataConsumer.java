package com.talentofuturo.geoSense_api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentofuturo.geoSense_api.dto.SensorDataMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataConsumer.class);

    private final ObjectMapper objectMapper;

    // In-memory storage for consumed messages
    private final List<SensorDataMessage> consumedMessages = new ArrayList<>();

    // Constructor para inyectar dependencias
    public SensorDataConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Consumes sensor data from the Kafka topic and stores it in memory.
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

            // Save the message to the in-memory list
            synchronized (consumedMessages) {
                consumedMessages.add(sensorDataMessage);
            }

            // Log the complete structure including the nested data
            if (sensorDataMessage.getJsonData() != null && !sensorDataMessage.getJsonData().isEmpty()) {
                SensorDataMessage.SensorReading firstReading = sensorDataMessage.getJsonData().get(0);
                logger.info(
                        "Sensor data processed - API Key: {}, First Reading - Temperature: {}°C, Humidity: {}%, Timestamp: {}",
                        sensorDataMessage.getApiKey(),
                        firstReading.getTemp(),
                        firstReading.getHumidity(),
                        firstReading.getDatetime());
            } else {
                logger.info("Sensor data stored in memory: {}", sensorDataMessage);
            }
        } catch (Exception e) {
            logger.error("Error processing sensor data message: {}", message, e);
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