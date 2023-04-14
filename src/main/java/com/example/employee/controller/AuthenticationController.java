package com.example.employee.controller;

import com.example.employee.model.dto.param.authentication.LoginParam;
import com.example.employee.service.AuthenticationService;
import com.example.employee.service.handler.response.ResponseData;
import com.example.employee.service.handler.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody @Validated LoginParam loginParam) {
        return new SuccessResponseData(authenticationService.login(loginParam));
    }
}
