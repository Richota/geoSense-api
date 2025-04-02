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

import com.talentofuturo.geoSense_api.controller.interfaces.IAdminController;
import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.service.AdminService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

/**
 * Implementation of IAdminController that handles administrative operations.
 */
@RestController
@RequestMapping("/api/admins")
@AllArgsConstructor
public class AdminController implements IAdminController {

    private final AdminService adminService;

    // @GetMapping("/ruta")
    // public ResponseEntity<String> getRuta() {
    // return ResponseEntity.ok("Ruta accesible");
    // }

    // @GetMapping("/{adminId}")
    // public ResponseEntity<String> getAdmin(@PathVariable Long adminId) {
    // return ResponseEntity.ok("Admin con ID: " + adminId);
    // }

    @GetMapping("/{adminId}")
    public ResponseEntity<String> getAdmin(@PathVariable Long adminId) {
        try {
            // Simula la lógica para verificar si el admin existe
            boolean adminExists = adminService.existsById(adminId); // Método ficticio, implementa en tu servicio
            if (!adminExists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID " + adminId + " not found");
            }
            return ResponseEntity.ok("Admin with ID " + adminId + " found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

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
    public ResponseEntity<List<Company>> getAllCompanies(@PathVariable Long adminId) {
        try {
            List<Company> companies = adminService.getAllCompaniesByAdmin(adminId);
            return ResponseEntity.ok(companies);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Admin no encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Error genérico
        }
    }
}
