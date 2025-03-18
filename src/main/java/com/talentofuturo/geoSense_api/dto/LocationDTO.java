package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationDTO {
	private Long id;
    private String locationName;
    private String locationCountry;
    private String locationCity;
    private String locationMeta;
}
