package com.talentofuturo.geoSense_api.controller;


import com.talentofuturo.geoSense_api.Service.AdminService;
import com.talentofuturo.geoSense_api.entity.Company;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    // Company Endpoints
    @PostMapping("/{adminId}/companies")
    public ResponseEntity<Company> createCompany(@PathVariable Long adminId, @RequestBody Company company) {
        return new ResponseEntity<>(adminService.createCompany(adminId, company), HttpStatus.CREATED);
    }

    @PutMapping("/companies/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestBody Company company) {
        return ResponseEntity.ok(adminService.updateCompany(companyId, company));
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        adminService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{adminId}/companies")
    public ResponseEntity<List<Company>> getAllCompanies(@PathVariable String adminUsername) {
        return ResponseEntity.ok(adminService.getAllCompaniesByAdmin(adminUsername));
    }
}