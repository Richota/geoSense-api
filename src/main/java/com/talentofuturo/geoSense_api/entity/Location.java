package com.talentofuturo.geoSense_api.entity;


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
    /**
     * Unique identifier for the location
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Name or identifier of the location
     */
    @Column(name = "locationName")
    private String locationName;
    
    /**
     * Country where the location is
     */
    @Column(name = "locationCountry")
    private String locationCountry;

    /**
     * City where the location is
     */
    @Column(name = "locationCity")
    private String locationCity;

    /**
     * Additional metadata or information about the location
     */
    @Column(name = "locationMeta")
    private String locationMeta;

    /**
     * Company that owns or manages this location
     * Many-to-one relationship with Company entity
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
