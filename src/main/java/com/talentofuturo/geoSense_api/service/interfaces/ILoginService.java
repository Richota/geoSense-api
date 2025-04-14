package com.talentofuturo.geoSense_api.service.interfaces;

import com.talentofuturo.geoSense_api.dto.AuthLoginRequest;
import com.talentofuturo.geoSense_api.dto.AuthLoginResponse;

public interface ILoginService {

    AuthLoginResponse loginAdmin(AuthLoginRequest adminRequest);

}