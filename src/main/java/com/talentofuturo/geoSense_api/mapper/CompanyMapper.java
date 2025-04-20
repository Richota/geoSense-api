package com.talentofuturo.geoSense_api.mapper;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getCompanyName(), company.getCompanyApiKey());
    }

    public static Company toEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        return company;
    }
}