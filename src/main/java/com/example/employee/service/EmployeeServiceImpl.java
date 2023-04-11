package com.example.employee.service;

import com.example.employee.exception.ServiceException;
import com.example.employee.exception.enums.EmployeeExceptionEnum;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.EmployeeResponse;
import com.example.employee.model.dto.param.EntityIdParam;
import com.example.employee.model.dto.param.employee.EmployeeAddParam;
import com.example.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public EmployeeResponse add(EmployeeAddParam employeeAddParam) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeAddParam, employee);
        Employee newEmployee = employeeRepository.save(employee);

        log.info("The Employee with name {} has been created", newEmployee.getName());

        return conversionService.convert(newEmployee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse getDetail(EntityIdParam entityIdParam) {
        Employee employee = employeeRepository.findById(entityIdParam.getId())
                .orElseThrow(() -> new ServiceException(EmployeeExceptionEnum.ENTITY_NOT_EXIST));
        return conversionService.convert(employee, EmployeeResponse.class);
    }
}
