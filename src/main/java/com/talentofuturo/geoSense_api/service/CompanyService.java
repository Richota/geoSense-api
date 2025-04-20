package com.talentofuturo.geoSense_api.service;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.repository.AdminRepository;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ICompanyService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final AdminRepository adminRepository;

    public CompanyDTO createCompany(Long adminId, CompanyDTO companyDTO) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with ID " + adminId + " not found"));

        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setAdmin(admin);

        Company savedCompany = companyRepository.save(company);

        return new CompanyDTO(savedCompany.getId(), savedCompany.getCompanyName(), savedCompany.getCompanyApiKey());
    }

    public CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company with ID " + companyId + " not found"));

        company.setCompanyName(companyDTO.getCompanyName());
        Company updatedCompany = companyRepository.save(company);

        return new CompanyDTO(updatedCompany.getId(), updatedCompany.getCompanyName(),
                updatedCompany.getCompanyApiKey());
    }

    public void deleteCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company with ID " + companyId + " not found");
        }
        companyRepository.deleteById(companyId);
    }

    public List<CompanyDTO> getAllCompaniesByAdmin(Long adminId) {
        List<Company> companies = companyRepository.findByAdminId(adminId);
        return companies.stream()
                .map(company -> new CompanyDTO(company.getId(), company.getCompanyName(), company.getCompanyApiKey()))
                .collect(Collectors.toList());
    }
}