package com.geosense.controller;

import com.geosense.service.KafkaProducerService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producerService;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
        producerService.sendMessage("geosense-topic", message);
        return "Message sent successfully";
    }
}