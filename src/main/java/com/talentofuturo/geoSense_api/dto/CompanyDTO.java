package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object for Company information.
 * Used for transferring company data between layers of the application.
 */
@Getter
@AllArgsConstructor
public class CompanyDTO {
    /**
     * Unique identifier for the company
     */
    private Long id;

    /**
     * Name of the company
     */
    private String companyName;
}
