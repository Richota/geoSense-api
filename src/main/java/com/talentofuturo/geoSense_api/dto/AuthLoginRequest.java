package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthLoginRequest {
    private String username;
    private String password;
}
