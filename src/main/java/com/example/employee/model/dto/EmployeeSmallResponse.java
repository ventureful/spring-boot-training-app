package com.example.employee.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class EmployeeSmallResponse {
    private BigInteger id;
    private String name;
    private String jobTitle;
}
