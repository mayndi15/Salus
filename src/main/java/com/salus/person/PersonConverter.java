package com.salus.person;

import com.salus.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public Person toModel(PersonJson pj, Long id) {

        Person p = new Person();
        p.setId(id);
        p.setFirstName(pj.firstName());
        p.setLastName(pj.lastName());
        p.setSocialName(pj.socialName());
        p.setGender(GenderEnum.valueOf(pj.gender()));
        p.setBirthday(DateUtils.convertStringToLocalDate(pj.birthday()));
        p.setDocument(DocumentEnum.valueOf(pj.document()));
        p.setValue(pj.value());
        p.setStatus(StatusEnum.valueOf(pj.status()));

        return p;
    }

    public PersonJson toJson(Person p) {

        return new PersonJson(p.getId(), p.getFirstName(), p.getLastName(), p.getSocialName(),
                p.getGender().toString(), DateUtils.convertLocalDateToString(p.getBirthday()),
                p.getDocument().toString(), p.getValue(), p.getStatus().toString(),
                (DateUtils.convertZonedDateTimeToString(p.getCreated())), (DateUtils.convertZonedDateTimeToString(p.getUpdated())));
    }
}
