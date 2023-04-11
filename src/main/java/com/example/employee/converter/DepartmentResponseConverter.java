package com.example.employee.converter;

import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.example.employee.model.dto.DepartmentResponse;
import com.example.employee.model.dto.EmployeeSmallResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentResponseConverter implements Converter<Department, DepartmentResponse> {

    @Override
    public DepartmentResponse convert(Department department) {
        DepartmentResponse departmentResponse = DepartmentResponse.builder().build();
        BeanUtils.copyProperties(department, departmentResponse);

        Employee employee = department.getEmployee();
        if (employee != null) {
            EmployeeSmallResponse employeeSmallResponse = EmployeeSmallResponse.builder().build();
            BeanUtils.copyProperties(employee, employeeSmallResponse);
            departmentResponse.setEmployee(employeeSmallResponse);
        }
        return departmentResponse;
    }
}
