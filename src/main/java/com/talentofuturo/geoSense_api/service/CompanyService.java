package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.mapper.CompanyMapper;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ICompanyService;

import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setCompanyName(companyDTO.getCompanyName());
                    Company updatedCompany = companyRepository.save(existingCompany);
                    return companyMapper.mapCompany(updatedCompany);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    @Override
    @Transactional
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company", "id", id);
        }
        companyRepository.deleteById(id);
    }
}
