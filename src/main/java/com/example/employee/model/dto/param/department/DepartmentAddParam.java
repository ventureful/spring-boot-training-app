package com.example.employee.model.dto.param.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentAddParam {
    @NotBlank(message = "name cannot be empty, please check the name parameter")
    private String name;
    private String description;

    private long employeeId;
}
