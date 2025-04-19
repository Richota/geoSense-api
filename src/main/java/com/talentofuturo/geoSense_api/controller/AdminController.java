package com.talentofuturo.geoSense_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.controller.interfaces.IAdminController;
import com.talentofuturo.geoSense_api.service.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admins")
@AllArgsConstructor
public class AdminController implements IAdminController {

    private final AdminService adminService;

    @GetMapping("/{adminId}")
    public ResponseEntity<String> getAdmin(@PathVariable Long adminId) {
        try {
            boolean adminExists = adminService.existsById(adminId);
            if (!adminExists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID " + adminId + " not found");
            }
            return ResponseEntity.ok("Admin with ID " + adminId + " found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}