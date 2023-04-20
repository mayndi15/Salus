package com.salus.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Boolean load(Long id) {

        return personRepository.findById(id).isPresent();
    }

    public Person details(Long id) {

        return personRepository.findById(id).orElseThrow();
    }

    public Person create(Person p) {

        setUpTimestamps(p);

        return personRepository.save(p);
    }

    public Person update(Person p, Long id) {
        Person pPersist = details(id);

        setUpdate(p, pPersist);

        setUpTimestamps(pPersist);

        return personRepository.save(pPersist);
    }

    public void delete(Long id) {
        Person pPersist = details(id);

        personRepository.delete(pPersist);
    }

    // PRIVATE METHODS
    private void setUpdate(Person p, Person pPersist) {
        pPersist.setFirstName(p.getFirstName());
        pPersist.setLastName(p.getLastName());
        pPersist.setSocialName(p.getSocialName());
    }

    private void setUpTimestamps(Person p) {
        if (p.getCreated() == null) {
            p.setCreated(ZonedDateTime.now());
        }
        p.setUpdated(ZonedDateTime.now());
    }
}
