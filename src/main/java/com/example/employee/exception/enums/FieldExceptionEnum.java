package com.example.employee.exception.enums;

import com.example.employee.exception.AbstractBaseExceptionEnum;
import com.example.employee.model.consts.ExceptionConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FieldExceptionEnum implements AbstractBaseExceptionEnum {

    FIELD_ERROR(1, "There is a field error");

    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return Integer.parseInt(ExceptionConstant.FIELD_EXCEPTION_ENUM + code);
    }
}
