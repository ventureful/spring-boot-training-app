package com.example.employee.service;

import com.example.employee.model.dto.AuthenticationResponse;
import com.example.employee.model.dto.param.authentication.LoginParam;

public interface AuthenticationService {
    AuthenticationResponse login(LoginParam loginParam);
}
