package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.exception.SalusExceptionEnum;
import com.salus.rest.BaseJson;
import com.salus.utils.DateUtils;
import com.salus.utils.DocumentUtils;
import com.salus.utils.EnumUtils;
import com.salus.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonControllerValidator {

    @Autowired
    private PersonService personService;

    public void validateCreate(BaseJson bj) throws SalusException {
        validateJson(bj);
        validateFirstName(bj.getPerson());
        validateLastName(bj.getPerson());
        validateGender(bj.getPerson());
        validateBirthday(bj.getPerson());
        validateDocument(bj.getPerson());
        validateValue(bj.getPerson());
    }

    public void validateUpdate(BaseJson bj, Long id) throws SalusException {
        validateStatus(bj.getPerson());
        validateJson(bj);
        validateId(id);
    }

    public void validateDetails(Long id) throws SalusException {
        validateId(id);
    }

    public void validateInactive(Long id) throws SalusException {
        validateId(id);
    }

    public void validateDelete(Long id) throws SalusException {
        validateId(id);
    }


    // PRIVATE METHODS
    private void validateId(Long id) throws SalusException {
        Person p = personService.load(id);

        if (p == null) {
            throw new SalusException(SalusExceptionEnum.ID_NOT_FOUND);
        }
    }

    private void validateJson(BaseJson bj) throws SalusException {
        if (bj == null) {
            throw new SalusException(SalusExceptionEnum.JSON_INVALID_FORMAT);
        }
    }

    private void validateStatus(PersonJson pj) throws SalusException {
        if (pj.status() != null && !EnumUtils.isEnumValid(pj.status(), StatusEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_STATUS);
        }
    }

    private void validateFirstName(PersonJson pj) throws SalusException {
        if (pj.firstName() != null && StringUtils.isBlank(pj.firstName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_FIRST_NAME);
        }
    }

    private void validateLastName(PersonJson pj) throws SalusException {
        if (pj.lastName() != null && StringUtils.isBlank(pj.lastName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_LAST_NAME);
        }
    }

    private void validateGender(PersonJson pj) throws SalusException {
        if (pj.gender() != null && !EnumUtils.isEnumValid(pj.gender(), GenderEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_GENDER);
        }
    }

    private void validateBirthday(PersonJson pj) throws SalusException {
        if (pj.birthday() != null && DateUtils.convertStringToLocalDate(pj.birthday()) == null) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_BIRTHDAY);
        }
    }

    private void validateDocument(PersonJson pj) throws SalusException {
        if (pj.document() != null && !EnumUtils.isEnumValid(pj.document(), DocumentEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT);
        }
    }

    private void validateValue(PersonJson pj) throws SalusException {
        if (pj.value() != null && !DocumentUtils.isCPF(pj.value())) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT_VALUE);
        }
    }
}
