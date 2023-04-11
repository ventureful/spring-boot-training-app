package com.example.employee.service;

import com.example.employee.model.dto.EmployeeResponse;
import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.employee.EmployeeAddParam;

public interface EmployeeService {
    EmployeeResponse add(EmployeeAddParam employeeAddParam);
    EmployeeResponse getDetail(EntityIdParam entityIdParam);
}
