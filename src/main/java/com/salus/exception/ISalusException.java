package com.salus.exception;

import org.springframework.http.HttpStatus;

public interface ISalusException {

    Long getCode();

    String getKey();

    HttpStatus getHttpStatus();

    String getReadableText(Object... args);
}
