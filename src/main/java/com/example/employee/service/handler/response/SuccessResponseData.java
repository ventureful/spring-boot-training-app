package com.example.employee.service.handler.response;

public class SuccessResponseData extends ResponseData {
    public SuccessResponseData(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }
}
