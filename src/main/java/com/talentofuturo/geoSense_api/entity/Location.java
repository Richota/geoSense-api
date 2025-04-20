package com.talentofuturo.geoSense_api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing a physical Location in the system.
 * Locations are associated with companies and contain geographical information.
 */
@Entity
@Data
@Table(name = "locations")
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
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensors = new ArrayList<>();
}