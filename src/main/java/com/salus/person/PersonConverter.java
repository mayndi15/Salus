package com.salus.person;

import com.salus.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    private DateUtils dateUtils;

    public Person toModel(PersonJson pj, Long id) {

        Person p = new Person();
        p.setId(id);
        p.setFirstName(pj.getFirstName());
        p.setLastName(pj.getLastName());
        p.setSocialName(pj.getSocialName());
        p.setGender(GenderEnum.valueOf(pj.getGender()));
        p.setBirthday(dateUtils.stringToDate(pj.getBirthday()));
        p.setPersonType(PersonTypeEnum.valueOf(pj.getPersonType()));
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
        pj.setBirthday(dateUtils.dateToString(p.getBirthday()));
        pj.setPersonType(p.getPersonType().toString());
        pj.setValue(p.getValue());
        pj.setCreated(dateUtils.dateToString(p.getCreated()));
        pj.setUpdated(dateUtils.dateToString(p.getUpdated()));

        return pj;
    }
}
