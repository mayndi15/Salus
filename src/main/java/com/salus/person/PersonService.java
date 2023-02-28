package com.salus.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person load(Long id) {

        return personRepository.findById(id).orElseThrow();
    }

    public Person create(Person p) {

        setUpTimestamps(p);

        return personRepository.save(p);
    }

    public Person update(Person p, Long id) {
        Person pPersist = load(id);

        pPersist.setFirstName(p.getFirstName());
        pPersist.setLastName(p.getLastName());
        pPersist.setSocialName(p.getSocialName());

        setUpTimestamps(pPersist);

        return personRepository.save(pPersist);
    }

    public void delete(Long id) {
        Person pPersist = load(id);

        personRepository.delete(pPersist);
    }


    // PRIVATE METHODS
    private void setUpTimestamps(Person p) {
        if (p.getCreated() == null) {
            p.setCreated(ZonedDateTime.now());
        }
        p.setUpdated(ZonedDateTime.now());
    }
}
