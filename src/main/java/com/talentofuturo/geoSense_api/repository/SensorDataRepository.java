package com.talentofuturo.geoSense_api.repository;

import com.talentofuturo.geoSense_api.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    // Encuentra datos de un sensor por su ID y un rango de tiempo
    List<SensorData> findBySensorIdAndTimestartBetween(Long sensorId, Instant start, Instant end);

    // Encuentra todos los datos de un sensor por su ID
    List<SensorData> findBySensorId(Long sensorId);

    // Encuentra el último dato de un sensor por su ID
    SensorData findFirstBySensorIdOrderByTimestartDesc(Long sensorId);

}
