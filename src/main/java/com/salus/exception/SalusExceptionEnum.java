package com.salus.exception;

import com.salus.utils.I18N;
import org.springframework.http.HttpStatus;

public enum SalusExceptionEnum implements ISalusException {

    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, 01L, "id.not.found");
    private Long code;
    private String key;
    private HttpStatus httpStatus;

    private SalusExceptionEnum(HttpStatus httpStatus, Long code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.key = message;
    }

    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getReadableText(Object... args) {
        return I18N.getString(this.getKey(), args);
    }
}
