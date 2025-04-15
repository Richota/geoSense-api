package com.talentofuturo.geoSense_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.mapper.CompanyMapper;
import com.talentofuturo.geoSense_api.repository.AdminRepository;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ICompanyService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final AdminRepository adminRepository;
    private final CompanyMapper companyMapper;

    public CompanyDTO createCompany(Long adminId, CompanyDTO companyDTO) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + adminId + " not found"));

        Company company = companyMapper.mapDTO(companyDTO);
        company.setAdmin(admin);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.mapCompany(savedCompany);
    }

    public CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found"));

        company.setCompanyName(companyDTO.getCompanyName());
        Company updatedCompany = companyRepository.save(company);

        return companyMapper.mapCompany(updatedCompany);
    }

    public void deleteCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
        }
        companyRepository.deleteById(companyId);
    }

    public List<CompanyDTO> getAllCompaniesByAdmin(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + adminId + " not found"));

        return admin.getCompanies().stream()
                .map(companyMapper::mapCompany)
                .collect(Collectors.toList());
    }
}
