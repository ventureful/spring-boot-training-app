package com.example.employee.service.handler;

import com.example.employee.exception.ServiceException;
import com.example.employee.exception.enums.FieldExceptionEnum;
import com.example.employee.model.consts.CommonConstant;
import com.example.employee.service.handler.response.ErrorResponseData;
import com.example.employee.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@Order(CommonConstant.EXCEPTION_HANDLER_ORDER)
@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData businessError(ServiceException e) {
        log.error("Business exception {}", e.getMessage());
        return renderJson(e.getCode(), e.getErrorMessage(), e, e.getData());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String argNotValidMessage = getArgNotValidMessage(e.getBindingResult());
        log.error("MethodArgumentNotValidException {}", argNotValidMessage);
        return renderJson(FieldExceptionEnum.FIELD_ERROR.getCode(), argNotValidMessage, null);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseData fieldError(BindException e) {
        String argNotValidMessage = getArgNotValidMessage(e.getBindingResult());
        log.error("BindException {}", argNotValidMessage);
        return renderJson(FieldExceptionEnum.FIELD_ERROR.getCode(), argNotValidMessage,  null);
    }

    private String getArgNotValidMessage(BindingResult bindingResult) {
        if (bindingResult == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<ObjectError> allErrorInfos = bindingResult.getAllErrors();
        String prefix = CommonConstant.COLON + CommonConstant.COLON;
        for (ObjectError error : allErrorInfos) {
            stringBuilder.append(prefix).append(error.getDefaultMessage());
        }
        return stringBuilder.toString().replaceFirst("^::", "");
    }

    private ErrorResponseData renderJson(Integer code, String message, Throwable e, Object data) {
        ErrorResponseData errorResponseData = renderJson(code, message, e);
        if (data != null) {
            errorResponseData.setData(data);
        }
        return errorResponseData;
    }

    private ErrorResponseData renderJson(Integer code, String message, Throwable e) {
        if (e != null) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            String exceptionClassTotalName = stackTraceElements[0].toString();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                if (stackTraceElement.toString().contains(CommonConstant.DEFAULT_PACKAGE_NAME)) {
                    exceptionClassTotalName = stackTraceElement.toString();
                    break;
                }
            }
            return ResponseUtil.responseDataError(code, message, exceptionClassTotalName);
        } else {
            return ResponseUtil.responseDataError(code, message, null);
        }
    }
}
