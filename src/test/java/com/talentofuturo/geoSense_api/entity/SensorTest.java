package com.talentofuturo.geoSense_api.entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    @Test
    void testSensorEntityCreation() {
        Sensor sensor = new Sensor();
        sensor.setSensorName("Temperature Sensor 1");
        sensor.setSensorCategory("Ambiental");
        sensor.setSensorStatus("Active");
        sensor.setSensorLatitude("-33.45694");
        sensor.setSensorLongitude("-70.64827");
        sensor.setSensorMeta("Outdoor installation");

        assertNull(sensor.getId());
        assertEquals("Temperature Sensor 1", sensor.getSensorName());
        assertEquals("Ambiental", sensor.getSensorCategory());
        assertEquals("Active", sensor.getSensorStatus());
        assertEquals("-33.45694", sensor.getSensorLatitude());
        assertEquals("-70.64827", sensor.getSensorLongitude());
        assertEquals("Outdoor installation", sensor.getSensorMeta());
    }

    @Test
    void testSensorApiKeyAutoGeneration() {
        Sensor sensor1 = new Sensor();
        Sensor sensor2 = new Sensor();

        String apiKey1 = sensor1.getSensorApiKey();
        String apiKey2 = sensor2.getSensorApiKey();

        assertNotNull(apiKey1);
        assertNotNull(apiKey2);
        assertNotEquals(apiKey1, apiKey2, "Cada sensor debe tener una API key única");
        
        // check if is a valid id
        assertDoesNotThrow(() -> {
            UUID.fromString(apiKey1);
            UUID.fromString(apiKey2);
        });
    }

    @Test
    void testSensorLocationRelationship() {
        Location location = new Location();
        location.setId(1L);
        location.setLocationName("Mina Principal");

        Sensor sensor = new Sensor();
        sensor.setSensorName("Vibration Sensor");
        sensor.setLocation(location);

        assertNotNull(sensor.getLocation());
        assertEquals(1L, sensor.getLocation().getId());
        assertEquals("Mina Principal", sensor.getLocation().getLocationName());
    }

    @Test
    void testSensorDataCollection() {
        Sensor sensor = new Sensor();
        sensor.setId(1L);

        SensorData data1 = new SensorData();
        data1.setId(100L);
        data1.setSensor(sensor);

        SensorData data2 = new SensorData();
        data2.setId(101L);
        data2.setSensor(sensor);

        sensor.setSensorData(List.of(data1, data2));

        assertEquals(2, sensor.getSensorData().size());
        assertTrue(sensor.getSensorData().stream().anyMatch(d -> d.getId().equals(100L)));
        assertTrue(sensor.getSensorData().stream().anyMatch(d -> d.getId().equals(101L)));
        assertEquals(1L, data1.getSensor().getId());
        assertEquals(1L, data2.getSensor().getId());
    }

    @Test
    void testToString() {
        Sensor sensor = new Sensor();
        sensor.setId(1L);
        sensor.setSensorName("Test Sensor");

        String toString = sensor.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("sensorName=Test Sensor"));
    }
}
