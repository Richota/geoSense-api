package com.talentofuturo.geoSense_api.controller;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.SensorDataMessage;
import com.talentofuturo.geoSense_api.kafka.SensorDataConsumer;
import com.talentofuturo.geoSense_api.kafka.SensorDataProducer;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/v1/sensors")
public class KafkaDataController {

    private String kafkaTopic;
    private final SensorDataProducer sensorDataProducer;
    private final SensorDataConsumer sensorDataConsumer;

    public KafkaDataController(
            SensorDataProducer sensorDataProducer,
            SensorDataConsumer sensorDataConsumer,
            @Value("${spring.kafka.topic}") String kafkaTopic) {
        this.sensorDataProducer = sensorDataProducer;
        this.sensorDataConsumer = sensorDataConsumer;
        this.kafkaTopic = kafkaTopic;
    }

    /**
     * Endpoint to send sensor data to Kafka.
     *
     * @param sensorDataMessage The sensor data message to send.
     * @return Response indicating the result of the operation.
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendSensorData(@RequestBody SensorDataMessage sensorDataMessage) {
        try {
            // Send the sensor data to Kafka
            sensorDataProducer.sendSensorData("test-topic", sensorDataMessage);
            return ResponseEntity.status(HttpStatus.OK).body("Sensor data sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send sensor data: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve consumed sensor data messages.
     *
     * @return List of SensorDataMessage objects.
     */
    @GetMapping("/consumed")
    public ResponseEntity<List<SensorDataMessage>> getConsumedMessages() {
        List<SensorDataMessage> messages = sensorDataConsumer.getConsumedMessages();
        return ResponseEntity.ok(messages);
    }
}