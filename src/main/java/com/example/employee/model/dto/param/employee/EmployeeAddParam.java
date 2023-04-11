package com.example.employee.model.dto.param.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeAddParam {
    @NotBlank(message = "name cannot be empty, please check the name parameter")
    private String name;
    private String jobTitle;
}
