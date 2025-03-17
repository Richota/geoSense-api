package com.talentofuturo.geoSense_api.mapper;


import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;

public class CompanyMapper {

    public static CompanyDTO mapCompany(Company company) {
        if (company == null) {
            return null;
        }

        return new CompanyDTO(
        	company.getId(),
            company.getCompanyName()
        );
    }

    public static Company mapDTO(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            return null;
        }

        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        return company;
    }
}