package com.example.employee.exception.enums;

import com.example.employee.exception.AbstractBaseExceptionEnum;
import com.example.employee.model.consts.ExceptionConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmployeeExceptionEnum implements AbstractBaseExceptionEnum {

    ENTITY_NOT_EXIST(1, "Employee does not exist");

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return Integer.parseInt(ExceptionConstant.EMPLOYEE_EXCEPTION_ENUM + code);
    }
}
