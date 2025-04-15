// package com.talentofuturo.geoSense_api.controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.talentofuturo.geoSense_api.dto.SensorDataMessage;
// import com.talentofuturo.geoSense_api.kafka.SensorDataConsumer;
// import com.talentofuturo.geoSense_api.kafka.SensorDataProducer;

// import lombok.AllArgsConstructor;

// @RestController
// @RequestMapping("/api/v1/sensors")
// @AllArgsConstructor
// public class SensorDataController {

// private final SensorDataProducer sensorDataProducer;

// /**
// * Endpoint to send sensor data to Kafka.
// *
// * @param sensorDataMessage The sensor data message to send.
// * @return Response indicating the result of the operation.
// */
// @PostMapping("/send")
// public ResponseEntity<String> sendSensorData(@RequestBody SensorDataMessage
// sensorDataMessage) {
// try {
// // Send the sensor data to Kafka
// sensorDataProducer.sendSensorData("sensor-data-topic", sensorDataMessage);
// return ResponseEntity.status(HttpStatus.OK).body("Sensor data sent
// successfully");
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// .body("Failed to send sensor data: " + e.getMessage());
// }
// }

// private final SensorDataConsumer sensorDataConsumer;

// /**
// * Endpoint to retrieve consumed sensor data messages.
// *
// * @return List of SensorDataMessage objects.
// */
// @GetMapping("/consumed")
// public ResponseEntity<List<SensorDataMessage>> getConsumedMessages() {
// List<SensorDataMessage> messages = sensorDataConsumer.getConsumedMessages();
// return ResponseEntity.ok(messages);
// }
// }