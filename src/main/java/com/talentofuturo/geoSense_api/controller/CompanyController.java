package com.talentofuturo.geoSense_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.controller.interfaces.ICompanyController;
import com.talentofuturo.geoSense_api.dto.CompanyDTO;
import com.talentofuturo.geoSense_api.exception.UnauthorizedException;
import com.talentofuturo.geoSense_api.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController implements ICompanyController {

    private final CompanyService companyService;

    // Endpoint para crear una compañía. Genera un company_api_key y lo incluye en la respuesta.
    @PostMapping("create/{adminId}")
    public ResponseEntity<CompanyDTO> createCompany(@PathVariable Long adminId, 
                                                     @RequestBody CompanyDTO companyDTO,
                                                     @RequestHeader(value = "company_api_key", required = false) String companyApiKey) {
        if (companyApiKey == null || !companyService.isValidApiKey(companyApiKey)) {
            throw new UnauthorizedException("Invalid company_api_key or missing authentication.");
        }

        // Genera un nuevo API key para la compañía
        String newApiKey = UUID.randomUUID().toString();
        companyDTO.setCompanyApiKey(newApiKey);

        // Crea la compañía y retorna la respuesta con el nuevo api_key.
        CompanyDTO createdCompany = companyService.createCompany(adminId, companyDTO);
        createdCompany.setCompanyApiKey(newApiKey); // asegurarse de que se devuelve el api_key generado
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    // Endpoint para actualizar una compañía, con validación del company_api_key
    @PutMapping("/update/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long companyId, 
                                                    @RequestBody CompanyDTO companyDTO,
                                                    @RequestHeader(value = "company_api_key", required = false) String companyApiKey) {
        if (companyApiKey == null || !companyService.isValidApiKey(companyApiKey)) {
            throw new UnauthorizedException("Invalid company_api_key or missing authentication.");
        }
        return ResponseEntity.ok(companyService.updateCompany(companyId, companyDTO));
    }

    // Endpoint para eliminar una compañía
    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId,
                                              @RequestHeader(value = "company_api_key", required = false) String companyApiKey) {
        if (companyApiKey == null || !companyService.isValidApiKey(companyApiKey)) {
            throw new UnauthorizedException("Invalid company_api_key or missing authentication.");
        }
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener todas las compañías de un administrador específico
    @GetMapping("getall/{adminId}")
    public ResponseEntity<List<CompanyDTO>> getAllCompaniesByAdmin(@PathVariable Long adminId,
                                                                   @RequestHeader(value = "company_api_key", required = false) String companyApiKey) {
        if (companyApiKey == null || !companyService.isValidApiKey(companyApiKey)) {
            throw new UnauthorizedException("Invalid company_api_key or missing authentication.");
        }
        List<CompanyDTO> companies = companyService.getAllCompaniesByAdmin(adminId);
        return ResponseEntity.ok(companies);
    }
}
