package com.example.employee.converter;

import com.example.employee.model.Employee;
import com.example.employee.model.dto.DepartmentResponse;
import com.example.employee.model.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeResponseConverter implements Converter<Employee, EmployeeResponse> {

    @Override
    public EmployeeResponse convert(Employee employee) {
        EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
        BeanUtils.copyProperties(employee, employeeResponse);
        if (!CollectionUtils.isEmpty(employee.getDepartments())) {
            List<DepartmentResponse> departments = employee.getDepartments().stream()
                    .map(department -> DepartmentResponse.builder()
                            .id(department.getId())
                            .name(department.getName())
                            .description(department.getDescription())
                            .build())
                    .collect(Collectors.toList());
            employeeResponse.setDepartments(departments);
        }
        return employeeResponse;
    }
}
