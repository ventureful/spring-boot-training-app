package com.example.employee.service;

import com.example.employee.model.dto.DepartmentResponse;
import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.department.DepartmentAddParam;

public interface DepartmentService {
    DepartmentResponse add(DepartmentAddParam departmentAddParam);
    DepartmentResponse getDetail(EntityIdParam entityIdParam);
}
