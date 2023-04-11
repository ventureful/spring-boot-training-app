package com.example.employee.controller;

import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.employee.EmployeeAddParam;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.handler.response.ResponseData;
import com.example.employee.service.handler.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseData add(@RequestBody @Validated EmployeeAddParam employeeAddParam) {
        return new SuccessResponseData(employeeService.add(employeeAddParam));
    }

    @GetMapping("/detail")
    public ResponseData getDetail(@Validated EntityIdParam entityIdParam) {
        return new SuccessResponseData(employeeService.getDetail(entityIdParam));
    }
}
