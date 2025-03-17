package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    @Column(name = "username")
    private String username;
       
    @Column(name = "password")
    private String password;

}
