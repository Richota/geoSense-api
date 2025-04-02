package com.talentofuturo.geoSense_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentofuturo.geoSense_api.dto.AuthLoginRequest;
import com.talentofuturo.geoSense_api.dto.LoginDTO;
import com.talentofuturo.geoSense_api.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthLoginRequest adminRequest) {
        return new ResponseEntity<>(loginService.loginAdmin(adminRequest), HttpStatus.OK);
    }

}
