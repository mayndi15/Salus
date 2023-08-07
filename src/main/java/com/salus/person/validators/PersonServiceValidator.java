package com.salus.person.validators;

import com.salus.exception.SalusException;
import com.salus.exception.SalusExceptionEnum;
import com.salus.person.Person;
import com.salus.person.PersonService;
import com.salus.person.enums.StatusEnum;
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
        validateDocument(p);
    }

    public void validateUpdate(Person p, Long id) throws SalusException {
        validateStatus(p);
        validateJson(p);
        validateId(id);
    }

    public void validateId(Long id) throws SalusException {
        Person p = personService.load(id);

        if (p == null || p.getId() == null) {
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

    private void validateDocument(Person p) throws SalusException {
        if (p.getDocument() != null && !DocumentUtils.isCPF(p.getDocument())) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT_VALUE);
        }
    }

    private void validateStatus(Person p) throws SalusException {
        if (p.getStatus() != null && !EnumUtils.isEnum(p.getStatus().toString(), StatusEnum.class)) {
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_STATUS);
        }
    }
}
