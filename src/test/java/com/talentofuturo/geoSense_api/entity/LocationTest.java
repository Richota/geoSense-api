package com.talentofuturo.geoSense_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testLocationEntityCreation() {
        Location location = new Location();
        location.setLocationName("Mina Principal");
        location.setLocationCountry("Chile");
        location.setLocationCity("Antofagasta");
        location.setLocationMeta("Zona de extracción norte");

        assertNull(location.getId());
        assertEquals("Mina Principal", location.getLocationName());
        assertEquals("Chile", location.getLocationCountry());
        assertEquals("Antofagasta", location.getLocationCity());
        assertEquals("Zona de extracción norte", location.getLocationMeta());
    }

    @Test
    void testCompanyRelationship() {
        Company company = new Company();
        company.setId(1L);
        company.setCompanyName("Mining Corp");

        Location location = new Location();
        location.setLocationName("Mina Sur");
        location.setCompany(company);

        assertNotNull(location.getCompany());
        assertEquals(1L, location.getCompany().getId());
        assertEquals("Mining Corp", location.getCompany().getCompanyName());
    }

    @Test
    void testEqualsAndHashCode() {
        Location location1 = new Location();
        location1.setId(1L);

        Location location2 = new Location();
        location2.setId(1L);

        Location location3 = new Location();
        location3.setId(2L);

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
        assertEquals(location1.hashCode(), location2.hashCode());
        assertNotEquals(location1.hashCode(), location3.hashCode());
    }

    @Test
    void testToString() {
        Location location = new Location();
        location.setId(1L);
        location.setLocationName("Test Location");
        location.setLocationCity("Santiago");

        String toString = location.toString();

        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("locationName=Test Location"));
        assertTrue(toString.contains("locationCity=Santiago"));
    }

    @Test
    void testLocationMetaEdgeCases() {
        Location location = new Location();

        location.setLocationMeta("");
        assertEquals("", location.getLocationMeta());

        String longMeta = " ".repeat(1000);
        location.setLocationMeta(longMeta);
        assertEquals(longMeta, location.getLocationMeta());

        location.setLocationMeta(null);
        assertNull(location.getLocationMeta());
    }

    @Test
    void testFieldLengthValidations() {
        Location location = new Location();

        String longName = "A".repeat(255);
        location.setLocationName(longName);
        assertEquals(longName, location.getLocationName());

        String cityWithSpecialChars = "San José de Maipo-Ñuñoa";
        location.setLocationCity(cityWithSpecialChars);
        assertEquals(cityWithSpecialChars, location.getLocationCity());
    }
}
