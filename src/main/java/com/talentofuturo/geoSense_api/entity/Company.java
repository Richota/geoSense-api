package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    private Long id;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @Column(name = "companyApiKey", unique = true, nullable = false, updatable = false)
    private String companyApiKey;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @PrePersist
    private void generateApiKey() {
        this.companyApiKey = UUID.randomUUID().toString();
    }
}