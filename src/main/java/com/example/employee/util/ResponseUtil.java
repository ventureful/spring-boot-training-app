package com.example.employee.util;

import com.example.employee.exception.AbstractBaseExceptionEnum;
import com.example.employee.exception.ServiceException;
import com.example.employee.service.handler.response.ErrorResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;

@Slf4j
public class ResponseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResponseUtil() {}

    public static ErrorResponseData responseDataError(Integer code, String message, String exceptionClass) {
        ErrorResponseData errorResponseData = new ErrorResponseData(code, message);
        errorResponseData.setExceptionClass(exceptionClass);
        return errorResponseData;
    }

    public static void setExceptionResponse(HttpServletResponse httpServletResponse, AbstractBaseExceptionEnum abstractBaseExceptionEnum) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponseData errorResponseData = new ErrorResponseData(abstractBaseExceptionEnum.getCode(), abstractBaseExceptionEnum.getMessage());
        errorResponseData.setExceptionClass(new ServiceException(abstractBaseExceptionEnum).getStackTrace()[0].toString());

        try {
            objectMapper.writeValue(httpServletResponse.getOutputStream(), errorResponseData);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
