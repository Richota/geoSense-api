package com.talentofuturo.geoSense_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.controller.interfaces.ICompanyController;
import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController implements ICompanyController {

    private final CompanyService companyService;

    @PostMapping("create/{adminId}")
    public ResponseEntity<CompanyDTO> createCompany(@PathVariable Long adminId, @RequestBody CompanyDTO companyDTO) {
        return new ResponseEntity<>(companyService.createCompany(adminId, companyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.updateCompany(companyId, companyDTO));
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getall/{adminId}")
    public ResponseEntity<List<CompanyDTO>> getAllCompaniesByAdmin(@PathVariable Long adminId) {
        List<CompanyDTO> companies = companyService.getAllCompaniesByAdmin(adminId);
        return ResponseEntity.ok(companies);
    }
}