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
        validateJson(bj);
        validateId(id);
    }

    public void validateDetails(Long id) throws SalusException {
        validateId(id);
    }

    public void validateDelete(Long id) throws SalusException {
        validateId(id);
    }


    // PRIVATE METHODS
    private void validateId(Long id) throws SalusException {
        Boolean hasId = personService.load(id);

        if (!hasId || id <= 0) {
            throw new SalusException(SalusExceptionEnum.ID_NOT_FOUND);
        }
    }

    private void validateJson(BaseJson bj) throws SalusException {
        if (bj == null) {
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
            throw new SalusException(SalusExceptionEnum.PERSON_INVALID_DOCUMENT_VALUE);
        }
    }
}
