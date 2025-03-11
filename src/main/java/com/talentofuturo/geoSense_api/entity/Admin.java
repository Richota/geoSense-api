package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admin {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;

}
