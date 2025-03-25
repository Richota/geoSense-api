package com.talentofuturo.geoSense_api.mapper;


import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;

/**
 * Implementation of Company mapping operations.
 * Handles conversion between Company entities and DTOs.
 */
public class CompanyMapper implements ICompanyMapper {
    /**
     * {@inheritDoc}
     */
    public static CompanyDTO mapCompany(Company company) {
        if (company == null) {
            return null;
        }

        return new CompanyDTO(
            company.getId(),
            company.getCompanyName()
        );
    }

    /**
     * {@inheritDoc}
     */
    public static Company mapDTO(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            return null;
        }

        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        return company;
    }
}