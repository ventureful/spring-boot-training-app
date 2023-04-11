package com.example.employee.service.handler.response;

import com.example.employee.model.consts.CommonConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseData {
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_ERROR_CODE = 500;
    public static final String DEFAULT_SUCCESS_MESSAGE = "Request Successful";

    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> messages;

    public ResponseData(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;

        String [] array = message.split(CommonConstant.COLON + CommonConstant.COLON);
        this.messages = array.length > 1 ? Arrays.asList(array) : null;
    }
}
