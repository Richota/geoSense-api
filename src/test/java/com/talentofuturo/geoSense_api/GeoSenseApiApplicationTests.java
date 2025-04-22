package com.talentofuturo.geoSense_api;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.time.Instant;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.beans.factory.annotation.Autowired;

// import com.talentofuturo.geoSense_api.dto.SensorDataDTO;
// import com.talentofuturo.geoSense_api.entity.Sensor;
// import com.talentofuturo.geoSense_api.entity.SensorData;
// import com.talentofuturo.geoSense_api.mapper.SensorDataMapper;

// @SpringBootTest
// class GeoSenseApiApplicationTests {

// @Autowired
// private SensorDataMapper sensorDataMapper; // Inyectar el mapper

// @Test
// public void testToDTO() {
// Sensor sensor = new Sensor();
// sensor.setId(1L);

// SensorData sensorData = new SensorData();
// sensorData.setId(100L);
// sensorData.setApiKey(25.5); // Cambiar setValue a setApiKey
// sensorData.setTimestart(Instant.now());
// sensorData.setTimeend(Instant.now().plusSeconds(3600));
// sensorData.setMeasurementType1("Temperature");
// sensorData.setSensor(sensor);

// SensorDataDTO dto = sensorDataMapper.toDTO(sensorData); // Usar el mapper
// inyectado

// assertEquals(100L, dto.getId());
// assertEquals(1L, dto.getSensorId());
// assertEquals(25.5, dto.getApiKey());
// }
// }