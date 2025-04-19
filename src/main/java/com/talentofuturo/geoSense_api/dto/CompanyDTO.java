package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object for Company information.
 * Used for transferring company data between layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
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

    /**
     * Value of the company API key
     */
    private String companyApiKey;
}

