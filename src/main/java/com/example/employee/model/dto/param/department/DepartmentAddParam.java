package com.example.employee.model.dto.param.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigInteger;

@Data
public class DepartmentAddParam {
    @NotBlank(message = "name cannot be empty, please check the name parameter")
    private String name;
    private String description;

    private BigInteger employeeId;
}
