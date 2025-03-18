package com.talentofuturo.geoSense_api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "locationName")
    private String locationName;
    
    @Column(name = "locationCountry")
    private String locationCountry;

    @Column(name = "locationCity")
    private String locationCity;

    @Column(name = "locationMeta")
    private String locationMeta;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
