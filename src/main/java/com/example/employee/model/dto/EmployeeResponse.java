package com.example.employee.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
public class EmployeeResponse {
    private BigInteger id;
    private String name;
    private String jobTitle;

    private List<DepartmentResponse> departments;
}
