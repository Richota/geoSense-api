package com.talentofuturo.geoSense_api.service.interfaces;

import com.talentofuturo.geoSense_api.dto.AuthLoginRequest;
import com.talentofuturo.geoSense_api.dto.LoginDTO;

public interface ILoginService {
    LoginDTO loginAdmin(AuthLoginRequest adminRequest);
}