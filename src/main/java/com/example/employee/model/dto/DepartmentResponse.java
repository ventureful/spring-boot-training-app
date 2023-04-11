package com.example.employee.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {
    private long id;
    private String name;
    private String description;

    private EmployeeSmallResponse employee;
}
