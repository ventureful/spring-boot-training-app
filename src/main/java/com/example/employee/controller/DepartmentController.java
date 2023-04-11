package com.example.employee.controller;

import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.department.DepartmentAddParam;
import com.example.employee.service.DepartmentService;
import com.example.employee.service.handler.response.ResponseData;
import com.example.employee.service.handler.response.SuccessResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseData add(@RequestBody @Validated DepartmentAddParam departmentAddParam) {
        return new SuccessResponseData(departmentService.add(departmentAddParam));
    }

    @GetMapping("/detail")
    public ResponseData getDetail(@Validated EntityIdParam entityIdParam) {
        return new SuccessResponseData(departmentService.getDetail(entityIdParam));
    }
}
