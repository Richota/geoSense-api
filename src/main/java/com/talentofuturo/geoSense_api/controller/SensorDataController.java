package com.talentofuturo.geoSense_api.controller;

import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.talentofuturo.geoSense_api.controller.interfaces.ISensorDataController;
import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
import com.talentofuturo.geoSense_api.entity.SensorData;
import com.talentofuturo.geoSense_api.service.SensorDataService;
import lombok.AllArgsConstructor;

/**
 * REST controller for managing sensor data operations.
 * Handles receiving sensor measurements and retrieving sensor data.
 */
@RestController
@RequestMapping("/api/v1/sensor-data")
@AllArgsConstructor
public class SensorDataController implements ISensorDataController {
    private final SensorDataService sensorDataService;

    @Override
    @PostMapping("/measurements")
    public ResponseEntity<String> receiveMeasurements(@RequestBody SensorDataDTO data) {
        sensorDataService.processSensorData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data saved successfully");
    }

    @Override
    @GetMapping("/sensors/{sensorId}")
    public ResponseEntity<List<SensorData>> getSensorMeasurements(
            @PathVariable Long sensorId,
            @RequestParam(required = false) Instant startDate,
            @RequestParam(required = false) Instant endDate) {
        return ResponseEntity.ok(sensorDataService.getMeasurements(sensorId, startDate, endDate));
    }

    @Override
    @GetMapping("/sensors/{sensorId}/latest")
    public ResponseEntity<SensorData> getLatestMeasurement(@PathVariable Long sensorId) {
        return ResponseEntity.ok(sensorDataService.getLatestMeasurement(sensorId));
    }
}
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
