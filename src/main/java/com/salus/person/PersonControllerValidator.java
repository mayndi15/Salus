package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.exception.SalusExceptionEnum;
import com.salus.utils.DateUtils;
import com.salus.utils.DocumentUtils;
import com.salus.utils.EnumUtils;
import com.salus.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonControllerValidator {

    public void validateCreate(PersonJson pj) throws SalusException {
        validateJson(pj);
        validateFirstName(pj);
        validateLastName(pj);
        validateGender(pj);
        validateBirthday(pj);
        validateDocument(pj);
        validateValue(pj);
    }

    public void validateUpdate(PersonJson pj, Long id) throws SalusException {
        validateJson(pj);
        validateId(id);
    }

    public void validateLoad(Long id) throws SalusException {
        validateId(id);
    }

    public void validateDelete(Long id) throws SalusException {
        validateId(id);
    }


    // PRIVATE METHODS
    private void validateId(Long id) throws SalusException {
        if (id == null || id <= 0) {
            throw new SalusException(SalusExceptionEnum.ID_NOT_FOUND);
        }
    }

    private void validateJson(PersonJson pj) throws SalusException {
        if (pj == null) {
            throw new SalusException(SalusExceptionEnum.JSON_INVALID_FORMAT);
        }
    }

    private void validateFirstName(PersonJson pj) throws SalusException {
        if (pj.getFirstName() != null && StringUtils.isBlank(pj.getFirstName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_FIRST_NAME);
        }
    }

    private void validateLastName(PersonJson pj) throws SalusException {
        if (pj.getLastName() != null && StringUtils.isBlank(pj.getLastName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_LAST_NAME);
        }
    }

    private void validateGender(PersonJson pj) throws SalusException {
        if (pj.getGender() != null && !EnumUtils.isEnumValid(pj.getGender(), GenderEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_GENDER);
        }
    }

    private void validateBirthday(PersonJson pj) throws SalusException {
        if (pj.getBirthday() != null && DateUtils.convertStringToLocalDate(pj.getBirthday()) == null) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_BIRTHDAY);
        }
    }

    private void validateDocument(PersonJson pj) throws SalusException {
        if (pj.getDocument() != null && !EnumUtils.isEnumValid(pj.getDocument(), DocumentEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT);
        }
    }

    private void validateValue(PersonJson pj) throws SalusException {
        if(pj.getValue() != null && !DocumentUtils.isCPF(pj.getValue())){
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_DOCUMENT_VALUE);
        }
    }
}
