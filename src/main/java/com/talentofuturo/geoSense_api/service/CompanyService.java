package com.talentofuturo.geoSense_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.mapper.CompanyMapper;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::mapCompany)
                .collect(Collectors.toList());
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.mapDTO(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return CompanyMapper.mapCompany(savedCompany);
    }
}
