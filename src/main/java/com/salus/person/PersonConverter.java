package com.salus.person;

import com.salus.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public Person toModel(PersonJson pj, Long id) {

        Person p = new Person();
        p.setId(id);
        p.setFirstName(pj.getFirstName());
        p.setLastName(pj.getLastName());
        p.setSocialName(pj.getSocialName());
        p.setGender(GenderEnum.valueOf(pj.getGender()));
        p.setBirthday(DateUtils.convertStringToLocalDate(pj.getBirthday()));
        p.setDocument(DocumentEnum.valueOf(pj.getDocument()));
        p.setValue(pj.getValue());

        return p;
    }

    public PersonJson toJson(Person p) {

        PersonJson pj = new PersonJson();
        pj.setId(p.getId());
        pj.setFirstName(p.getFirstName());
        pj.setLastName(p.getLastName());
        pj.setSocialName(p.getSocialName());
        pj.setGender(p.getGender().toString());
        pj.setBirthday(DateUtils.convertLocalDateToString(p.getBirthday()));
        pj.setDocument(p.getDocument().toString());
        pj.setValue(p.getValue());
        pj.setCreated(DateUtils.convertZonedDateTimeToString(p.getCreated()));
        pj.setUpdated(DateUtils.convertZonedDateTimeToString(p.getUpdated()));

        return pj;
    }
}
