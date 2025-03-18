package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SensorDTO {
	private Long id;
    private String sensorName;
    private String sensorCategory;
    private String sensorMeta;

}
