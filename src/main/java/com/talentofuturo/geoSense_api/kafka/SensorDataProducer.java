// package com.talentofuturo.geoSense_api.kafka;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Service;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.talentofuturo.geoSense_api.dto.SensorDataMessage;

// @Service
// public class SensorDataProducer {

// private static final Logger logger =
// LoggerFactory.getLogger(SensorDataProducer.class);
// private final KafkaTemplate<String, String> kafkaTemplate;
// private final ObjectMapper objectMapper;

// public SensorDataProducer(KafkaTemplate<String, String> kafkaTemplate,
// ObjectMapper objectMapper) {
// this.kafkaTemplate = kafkaTemplate;
// this.objectMapper = objectMapper;
// }

// /**
// * Sends sensor data to the specified Kafka topic.
// *
// * @param topic The Kafka topic to send the data to.
// * @param message The sensor data message to send.
// */
// public void sendSensorData(String topic, SensorDataMessage message) {
// try {
// // Serialize the SensorDataMessage object to JSON
// String jsonMessage = objectMapper.writeValueAsString(message);
// logger.info("Sending sensor data: {} to topic: {}", jsonMessage, topic);

// // Send the JSON message to the Kafka topic
// kafkaTemplate.send(topic, jsonMessage);
// } catch (Exception e) {
// logger.error("Failed to send sensor data to topic: {}", topic, e);
// }
// }
// }