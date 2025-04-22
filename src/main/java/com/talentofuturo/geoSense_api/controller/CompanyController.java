package com.talentofuturo.geoSense_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.service.interfaces.ICompanyService;
import com.talentofuturo.geoSense_api.controller.interfaces.ICompanyController;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController implements ICompanyController {

    private final ICompanyService companyService;

    @GetMapping("/get/{companyId}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long companyId) {
        CompanyDTO company = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(company);
    }

    @PostMapping("/create/{adminId}")
    public ResponseEntity<CompanyDTO> createCompany(@PathVariable Long adminId, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO createdCompany = companyService.createCompany(adminId, companyDTO);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long companyId, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO updatedCompany = companyService.updateCompany(companyId, companyDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getall/{adminId}")
    public ResponseEntity<List<CompanyDTO>> getAllCompaniesByAdmin(@PathVariable Long adminId) {
        List<CompanyDTO> companies = companyService.getAllCompaniesByAdmin(adminId);
        return ResponseEntity.ok(companies);
    }

}