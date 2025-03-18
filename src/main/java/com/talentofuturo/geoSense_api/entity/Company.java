package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Data
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "companyName")
    @NotNull
    private String CompanyName;

    @Column(name = "companyApiKey")
    @NotNull
    private String CompanyApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "admin_username")
    private Admin admin;

}
