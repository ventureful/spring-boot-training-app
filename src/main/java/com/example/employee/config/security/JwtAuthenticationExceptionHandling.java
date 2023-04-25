package com.example.employee.config.security;

import com.example.employee.exception.enums.AuthenticationExceptionEnum;
import com.example.employee.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationExceptionHandling implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException authenticationException) throws IOException {
        log.error("Unauthorized error: {}", authenticationException.getMessage());

        if (authenticationException instanceof InsufficientAuthenticationException) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ResponseUtil.setExceptionResponse(httpServletResponse, AuthenticationExceptionEnum.JWT_IS_INVALID);
            return;
        }

        setDefaultErrorResponse(httpServletRequest, httpServletResponse, authenticationException);
    }

    private void setDefaultErrorResponse(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         AuthenticationException authenticationException) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authenticationException.getMessage());
        body.put("path", httpServletRequest.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(httpServletResponse.getOutputStream(), body);
    }
}
