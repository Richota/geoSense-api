package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Company {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CompanyName;
    private String CompanyApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "Admin_username")
    private Admin admin;
}
