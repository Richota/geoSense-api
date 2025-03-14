package com.talentofuturo.geoSense_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Data
public class Company {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @NotNull
    private String CompanyName;
    private String CompanyApiKey = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "admin_username")
    private Admin admin;


}
