package com.talentofuturo.geoSense_api.service;

import com.talentofuturo.geoSense_api.entity.Admin;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.repository.AdminRepository;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.service.interfaces.IAdminService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementation of administrative operations for company management.
 */
@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;

    public Company createCompany(Long adminId, Company company) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado"));
        company.setAdmin(admin);
        return companyRepository.save(company);
    }

    public Company updateCompany(Long companyId, Company companyDetails) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Compañía no encontrada"));

        company.setCompanyName(companyDetails.getCompanyName());
        return companyRepository.save(company);
    }

    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    public List<Company> getAllCompaniesByAdmin(String adminUsername) {
        return companyRepository.findByAdminUsername(adminUsername);
    }

    }
