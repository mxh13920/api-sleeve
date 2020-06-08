package com.meng.sleeve.exception.http;

public class ServiceException extends HttpException {
    public ServiceException(int code){
        this.code=code;
        this.httpStatusCode=500;
    }
}
