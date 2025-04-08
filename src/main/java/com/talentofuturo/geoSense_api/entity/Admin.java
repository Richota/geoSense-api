package com.talentofuturo.geoSense_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an Administrator in the system.
 * Administrators can manage multiple companies and have secure authentication credentials.
 */
@Entity
@Table(name = "admins")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    /**
     * Unique identifier for the administrator
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Unique username for authentication
     */
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    /**
     * Encrypted password for authentication
     */
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    /**
     * Encrypts the password using BCrypt before storing
     * List of companies managed by this administrator
     * Relationship with Company entity
     */
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Company> companies = new ArrayList<>();
}
