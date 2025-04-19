package com.talentofuturo.geoSense_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {
    private String username;
    private String message;
    private String jwt;
    boolean status;

}
