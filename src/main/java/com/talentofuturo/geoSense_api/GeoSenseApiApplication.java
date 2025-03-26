package com.talentofuturo.geoSense_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for GeoSense API.
 * Initializes and bootstraps the Spring application context.
 */
@SpringBootApplication
public class GeoSenseApiApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(GeoSenseApiApplication.class, args);
    }
}
