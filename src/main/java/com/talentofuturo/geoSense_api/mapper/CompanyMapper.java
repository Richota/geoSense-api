package com.talentofuturo.geoSense_api.mapper;


import org.springframework.stereotype.Component;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.mapper.interfaces.ICompanyMapper;

/**
 * Implementation of Company mapping operations.
 * Handles conversion between Company entities and DTOs.
 */
@Component
public class CompanyMapper implements ICompanyMapper {
    
    public CompanyDTO mapCompany(Company company) {
        if (company == null) {
            return null;
        }
        return new CompanyDTO(
            company.getId(),
            company.getCompanyName()
        );
    }

    public Company mapDTO(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            return null;
        }
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        return company;
    }
}