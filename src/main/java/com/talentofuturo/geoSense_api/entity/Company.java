package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "companies")
public class Company {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "companyName")
    private String companyName;
    
    @Column(name = "companyApiKey")
    private String companyApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "Admin_username")
    private Admin admin;
}
