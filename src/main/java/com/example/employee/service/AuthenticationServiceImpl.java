package com.example.employee.service;

import com.example.employee.config.security.JwtUtil;
import com.example.employee.model.dto.AuthenticationResponse;
import com.example.employee.model.dto.param.authentication.LoginParam;
import com.example.employee.service.handler.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public AuthenticationResponse login(LoginParam loginParam) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtil.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .username(userDetails.getUsername())
                .build();
    }
}
