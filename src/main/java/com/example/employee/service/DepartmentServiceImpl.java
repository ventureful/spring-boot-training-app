package com.example.employee.service;

import com.example.employee.exception.ServiceException;
import com.example.employee.exception.enums.DepartmentExceptionEnum;
import com.example.employee.exception.enums.EmployeeExceptionEnum;
import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.DepartmentResponse;
import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.department.DepartmentAddParam;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public DepartmentResponse add(DepartmentAddParam departmentAddParam) {
        Employee employee = employeeRepository.findById(departmentAddParam.getEmployeeId())
                .orElseThrow(() -> new ServiceException(EmployeeExceptionEnum.ENTITY_NOT_EXIST));
        Department department = new Department();
        BeanUtils.copyProperties(departmentAddParam, department);
        department.setEmployee(employee);
        departmentRepository.save(department);
        return conversionService.convert(department, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse getDetail(EntityIdParam entityIdParam) {
        Department department = departmentRepository.findById(entityIdParam.getId())
                .orElseThrow(() -> new ServiceException(DepartmentExceptionEnum.ENTITY_NOT_EXIST));
        return conversionService.convert(department, DepartmentResponse.class);
    }
}
