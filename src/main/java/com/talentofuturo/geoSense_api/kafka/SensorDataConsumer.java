package com.talentofuturo.geoSense_api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentofuturo.geoSense_api.dto.SensorDataMessage;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SensorDataConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SensorDataConsumer.class);

    private final ObjectMapper objectMapper;

    // In-memory storage for consumed messages
    private final List<SensorDataMessage> consumedMessages = new ArrayList<>();

    /**
     * Consumes sensor data from the Kafka topic and stores it in memory.
     *
     * @param message The JSON message received from Kafka.
     */
    @KafkaListener(topics = "test-topic", groupId = "geoSense-group")
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

            logger.info("Sensor data stored in memory: {}", sensorDataMessage);
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