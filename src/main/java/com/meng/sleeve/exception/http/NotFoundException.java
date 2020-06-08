package com.meng.sleeve.exception.http;

import javax.xml.ws.http.HTTPException;

public class NotFoundException  extends HttpException {
    public NotFoundException(int code) {
        this.code=code;
        this.httpStatusCode=404;
    }
}
