package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

/**
 * Entity representing a Company in the system.
 * Companies are managed by administrators and are identified by unique API keys.
 */
@Entity
@Data
@Table(name = "companies")
public class Company {
    /**
     * Unique identifier for the company
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the company
     */
    @Column(name = "companyName")
    @NotNull
    private String CompanyName;

    /**
     * Unique API key for company authentication
     * Automatically generated using UUID
     */
    @Column(name = "companyApiKey")
    @NotNull
    private String CompanyApiKey = UUID.randomUUID().toString();

    /**
     * Administrator who manages this company
     * Many-to-one relationship with Admin entity
     */
    @ManyToOne
    @JoinColumn(name = "admin_username")
    private Admin admin;
}
