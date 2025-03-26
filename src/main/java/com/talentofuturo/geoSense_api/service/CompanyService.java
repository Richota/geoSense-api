package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.mapper.CompanyMapper;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ICompanyService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of company management operations.
 */
@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::mapCompany)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.mapDTO(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.mapCompany(savedCompany);
    }
}
