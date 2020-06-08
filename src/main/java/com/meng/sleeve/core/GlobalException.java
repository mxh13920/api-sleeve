package com.meng.sleeve.core;

import com.meng.sleeve.core.configuration.ExceptionConfiguration;
import com.meng.sleeve.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalException {

    @Autowired
    private ExceptionConfiguration exceptionConfiguration;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(9999, "服务器异常", method + ":" + requestURI);
        return message;
    }

    //    处理定义过的Exception
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e) {
//        获取方法和路径
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
//        设置响应内容
        UnifyResponse message = new UnifyResponse(e.getCode(), exceptionConfiguration.getMessage(e.getCode()), method + ":" + requestURI);
//        设置响应头和转态
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> responseResponseEntity = new ResponseEntity<>(message, httpHeaders, httpStatus);
        return responseResponseEntity;
    }

    //    参数验证异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public UnifyResponse getMethodArgumentNotValidException(HttpServletRequest request
            , MethodArgumentNotValidException e) {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errors = getErrors(allErrors);
        UnifyResponse unifyResponse = new UnifyResponse(10001, errors, method + ":" + requestURI);
        return unifyResponse;
    }
    //    将验证错误信息转化为string
    public String getErrors(List<ObjectError> errors) {
        StringBuffer stringBuffer = new StringBuffer();
        errors.forEach(e -> {
            stringBuffer.append(e.getDefaultMessage()).append(",");
        });
        return stringBuffer.toString();
    }


    //    路径校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyResponse getConstraintViolationException(HttpServletRequest request
                                                                            , ConstraintViolationException e) {
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        for (ConstraintViolation<?> error:constraintViolations){
//            ConstraintViolation<?> a=error;
//        }
        String message = e.getMessage();
        return new UnifyResponse(10001,message,method+":"+requestURI);
    }


}
