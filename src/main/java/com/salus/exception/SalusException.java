package com.salus.exception;

import org.springframework.http.HttpStatus;

public class SalusException extends Exception {

    private static final long serialVersionUID = -187500547480316378L;

    private ISalusException codeEnum;

    private String message;

    public SalusException(ISalusException errorCode, Object... vars) {
        this.codeEnum = errorCode;
        this.message = errorCode.getReadableText(vars);
    }

    public ISalusException getCodeEnum() {
        return codeEnum;
    }

    public Long getCode() {
        return this.codeEnum.getCode();
    }

    public HttpStatus getHttpStatus() {
        return this.codeEnum.getHttpStatus();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
