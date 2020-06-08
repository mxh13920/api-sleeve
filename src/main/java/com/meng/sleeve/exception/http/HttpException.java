package com.meng.sleeve.exception.http;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode=500;
}
