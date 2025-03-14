package com.talentofuturo.geoSense_api.repository;

import com.talentofuturo.geoSense_api.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByAdminUsername(String adminUsername);
}
