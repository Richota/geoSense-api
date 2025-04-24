package com.talentofuturo.geoSense_api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a physical Location in the system.
 * Locations are associated with companies and contain geographical information.
 */
@Entity
@Data
@Table(name = "locations")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    private Long id;

    @Column(name = "locationName", nullable = false)
    private String locationName;

    @Column(name = "locationCountry")
    private String locationCountry;

    @Column(name = "locationCity")
    private String locationCity;

    @Column(name = "locationMeta")
    private String locationMeta;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore // Evita la serialización completa de la compañía
    private Company company;

    @OneToMany(mappedBy = "location")
    @JsonIgnore // Evita la serialización de los sensores
    private List<Sensor> sensors;
}