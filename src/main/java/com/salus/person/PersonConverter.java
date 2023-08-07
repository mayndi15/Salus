package com.salus.person;

import com.salus.person.enums.DocumentTypeEnum;
import com.salus.person.enums.GenderEnum;
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
        p.setTypeDocument(DocumentTypeEnum.valueOf(pj.typeDocument()));
        p.setDocument(pj.document());

        return p;
    }

    public PersonJson toJson(Person p) {

        return new PersonJson(
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                p.getSocialName(),
                p.getGender().toString(),
                DateUtils.convertLocalDateToString(p.getBirthday()),
                p.getTypeDocument().toString(),
                p.getDocument(),
                p.getStatus().toString(),
                (DateUtils.convertZonedDateTimeToString(p.getCreated())),
                (DateUtils.convertZonedDateTimeToString(p.getUpdated())));
    }
}
