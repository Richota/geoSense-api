package com.talentofuturo.geoSense_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Company information.
 * Used for transferring company data between layers of the application.
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
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
