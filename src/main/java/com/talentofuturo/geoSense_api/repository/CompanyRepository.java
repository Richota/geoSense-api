package com.talentofuturo.geoSense_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentofuturo.geoSense_api.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByAdminId(Long adminId);
}