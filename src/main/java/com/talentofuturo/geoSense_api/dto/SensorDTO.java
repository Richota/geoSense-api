package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for Sensor entity, without circular references.
 * Es inmutable para garantizar la integridad de los datos.
 */

@Getter
@AllArgsConstructor
public class SensorDTO {
    private final Long id;
    private final String sensorName;
    private final String sensorCategory;
    private final String sensorMeta;
    private final String sensorApiKey;
    private final String companyApiKey;
    private final Long locationId;
}