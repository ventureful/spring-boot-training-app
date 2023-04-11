package com.example.employee.service.handler.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseData extends ResponseData {
    private String exceptionClass;

    public ErrorResponseData(String message) {
        super(false, DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }
}
