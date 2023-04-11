package com.example.employee.util;

import com.example.employee.service.handler.response.ErrorResponseData;

public class ResponseUtil {
    private ResponseUtil() {}

    public static ErrorResponseData responseDataError(Integer code, String message, String exceptionClass) {
        ErrorResponseData errorResponseData = new ErrorResponseData(code, message);
        errorResponseData.setExceptionClass(exceptionClass);
        return errorResponseData;
    }
}
