package com.talentofuturo.geoSense_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.controller.interfaces.ICompanyController;
import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.service.CompanyService;

/**
 * Implementation of ICompanyController that handles company operations.
 */
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController implements ICompanyController {

    @Autowired
    private CompanyService companyService;

    // @GetMapping
    // public List<CompanyDTO> getAllCompanies() {
    // return companyService.getAllCompanies();
    // }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.createCompany(companyDTO);
    }
}
