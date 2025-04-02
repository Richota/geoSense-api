package com.talentofuturo.geoSense_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an Administrator in the system.
 * Administrators can manage multiple companies and have secure authentication
 * credentials.
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
     * 
     * @param password The text password to be encrypted
     */
    // public void setPassword(String password) {
    // this.password = new BCryptPasswordEncoder().encode(password);
    // }

    /**
     * List of companies managed by this administrator
     * Relationship with Company entity
     */
    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies = new ArrayList<>();

}
