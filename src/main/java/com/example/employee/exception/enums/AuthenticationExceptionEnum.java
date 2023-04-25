package com.example.employee.exception.enums;

import com.example.employee.exception.AbstractBaseExceptionEnum;
import com.example.employee.model.consts.ExceptionConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthenticationExceptionEnum implements AbstractBaseExceptionEnum {

    JWT_IS_INVALID(1, "Jwt token is not valid.");
    
    private final Integer code;
    private final String message;

    @Override
    public Integer getCode() {
        return Integer.parseInt(ExceptionConstant.AUTHENTICATION_EXCEPTION_ENUM + code);
    }
}
