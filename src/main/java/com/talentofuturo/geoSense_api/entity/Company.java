package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

@Entity
@Data
@Table(name = "companies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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