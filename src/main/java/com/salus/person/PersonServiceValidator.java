package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.exception.SalusExceptionEnum;
import com.salus.utils.DocumentUtils;
import com.salus.utils.EnumUtils;
import com.salus.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceValidator {

    @Autowired
    private PersonService personService;

    public void validateCreate(Person p) throws SalusException {
        validateJson(p);
        validateFirstName(p);
        validateLastName(p);
        validateValue(p);
    }

    public void validateUpdate(Person p, Long id) throws SalusException {
        validateStatus(p);
        validateJson(p);
        validateId(id);
    }

    public void validateInactive(Long id) throws SalusException {
        validateId(id);
    }

    public void validateDelete(Long id) throws SalusException {
        validateId(id);
    }

    public void validateId(Long id) throws SalusException {
        Person p = personService.load(id);

        if (p.getId() == null) {
            throw new SalusException(SalusExceptionEnum.ID_NOT_FOUND);
        }
    }

    // PRIVATE METHODS
    private void validateJson(Person p) throws SalusException {
        if (p == null) {
            throw new SalusException(SalusExceptionEnum.JSON_INVALID_FORMAT);
        }
    }

    private void validateFirstName(Person p) throws SalusException {
        if (p.getFirstName() != null && StringUtils.isBlank(p.getFirstName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_FIRST_NAME);
        }
    }

    private void validateLastName(Person p) throws SalusException {
        if (p.getLastName() != null && StringUtils.isBlank(p.getLastName())) {
            throw new SalusException(SalusExceptionEnum.PERSON_WITHOUT_LAST_NAME);
        }
    }

    private void validateValue(Person p) throws SalusException {
        if (p.getValue() != null && !DocumentUtils.isCPF(p.getValue())) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT_VALUE);
        }
    }

    private void validateStatus(Person p) throws SalusException {
        if (p.getStatus() != null && !EnumUtils.isEnumValid(p.getStatus().toString(), StatusEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_STATUS);
        }
    }
}
