package com.example.employee.model.dto.param.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginParam {
    @NotBlank(message = "username cannot be empty, please check the username parameter")
    private String username;
    @NotBlank(message = "password cannot be empty, please check the password parameter")
    private String password;
}
