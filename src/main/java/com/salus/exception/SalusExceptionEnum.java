package com.salus.exception;

import com.salus.utils.I18N;
import org.springframework.http.HttpStatus;

public enum SalusExceptionEnum implements ISalusException {

    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, 01L, "id.not.found"),
    JSON_INVALID_FORMAT(HttpStatus.BAD_REQUEST, 02L, "json.invalid.format"),


    PERSON_WITHOUT_FIRST_NAME(HttpStatus.BAD_REQUEST, 11L, "person.without.first.name"),
    PERSON_WITHOUT_LAST_NAME(HttpStatus.BAD_REQUEST, 12L, "person.without.last.name"),
    PERSON_WITHOUT_GENDER(HttpStatus.BAD_REQUEST, 13L, "person.without.gender"),
    PERSON_WITHOUT_BIRTHDAY(HttpStatus.BAD_REQUEST, 14L, "person.without.birthday"),
    PERSON_INVALID_DOCUMENT(HttpStatus.BAD_REQUEST, 15L, "person.invalid.document"),
    PERSON_INVALID_DOCUMENT_VALUE(HttpStatus.BAD_REQUEST, 16L, "person.invalid.document.value");

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
