package com.geosense.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "geosense-topic", groupId = "geosense-group")
    public void listen(String message) {
        log.info("Received message: {}", message);
        // Agregar lógica de procesamiento de mensajes aquí
    }
}