package com.talentofuturo.geoSense_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talentofuturo.geoSense_api.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
