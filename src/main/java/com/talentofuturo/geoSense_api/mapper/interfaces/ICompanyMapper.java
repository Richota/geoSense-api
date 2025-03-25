package com.talentofuturo.geoSense_api.mapper.interfaces;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;

/**
 * Interface defining mapping operations between Company entities and DTOs.
 */
public interface ICompanyMapper {
    /**
     * Converts a Company entity to a CompanyDTO
     *
     * @param company The Company entity to convert
     * @return The corresponding CompanyDTO, or null if input is null
     */
    CompanyDTO mapCompany(Company company);

    /**
     * Converts a CompanyDTO to a Company entity
     *
     * @param companyDTO The CompanyDTO to convert
     * @return The corresponding Company entity, or null if input is null
     */
    Company mapDTO(CompanyDTO companyDTO);
}