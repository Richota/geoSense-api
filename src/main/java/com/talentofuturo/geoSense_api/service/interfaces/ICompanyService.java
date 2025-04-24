package com.talentofuturo.geoSense_api.service.interfaces;

import java.util.List;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;

/**
 * Interface defining the contract for CompanyService.
 */
public interface ICompanyService {

    CompanyDTO createCompany(Long adminId, CompanyDTO companyDTO);

    CompanyDTO updateCompany(Long companyId, CompanyDTO companyDTO);

    void deleteCompany(Long companyId);

    List<CompanyDTO> getAllCompaniesByAdmin(Long adminId);

    CompanyDTO getCompanyById(Long companyId);
}