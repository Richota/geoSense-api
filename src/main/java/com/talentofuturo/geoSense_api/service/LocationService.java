package com.talentofuturo.geoSense_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentofuturo.geoSense_api.entity.Company;
import com.talentofuturo.geoSense_api.entity.Location;
import com.talentofuturo.geoSense_api.exception.ResourceNotFoundException;
import com.talentofuturo.geoSense_api.repository.CompanyRepository;
import com.talentofuturo.geoSense_api.repository.LocationRepository;
import com.talentofuturo.geoSense_api.service.interfaces.ILocationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;
    private final CompanyRepository companyRepository;

    @Override
    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location with ID " + locationId + " not found"));
    }

    @Override
    public Location createLocationByApiKey(String companyApiKey, Location location) {
        // Busca la compañía por su API key
        Company company = companyRepository.findByCompanyApiKey(companyApiKey)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with API key: " + companyApiKey));

        // Asocia la compañía a la ubicación
        location.setCompany(company);

        // Guarda la ubicación en la base de datos
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long locationId, Location locationDetails) {
        Location location = getLocationById(locationId);
        location.setLocationName(locationDetails.getLocationName());
        location.setLocationCity(locationDetails.getLocationCity());
        location.setLocationCountry(locationDetails.getLocationCountry());
        location.setLocationMeta(locationDetails.getLocationMeta());
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long locationId) {
        if (!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException("Location with ID " + locationId + " not found");
        }
        locationRepository.deleteById(locationId);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}