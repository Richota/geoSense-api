package com.talentofuturo.geoSense_api.controller;

import com.talentofuturo.geoSense_api.controller.interfaces.ICompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.service.CompanyService;
import java.util.List;

/**
 * Implementation of ICompanyController that handles company operations.
 */
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController implements ICompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.createCompany(companyDTO);
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return companyService.updateCompany(id, companyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
