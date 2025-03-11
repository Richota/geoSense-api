package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String locationName;
    private String locationCountry;
    private String locationCity;
    private String locationMeta;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
