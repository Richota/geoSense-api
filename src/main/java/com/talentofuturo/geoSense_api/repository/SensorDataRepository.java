package com.talentofuturo.geoSense_api.repository;

import com.talentofuturo.geoSense_api.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findBySensorIdInAndTimestartBetween(List<Long> sensorIds, Instant start, Instant end);

    List<SensorData> findBySensorId(Long sensorId);

    SensorData findFirstBySensorIdOrderByTimestartDesc(Long sensorId);
}