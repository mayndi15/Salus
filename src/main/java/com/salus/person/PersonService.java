package com.salus.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person load(Long id) {

        if (id == null) {
            return null;
        }

        return personRepository.getReferenceById(id);
    }

    public Person create(Person p) {

        setUpTimestamps(p);

        return personRepository.save(p);
    }

    public Person update(Person p, Long id) {
        Person pPersist = load(id);

        setUpdate(p, pPersist);

        setUpTimestamps(pPersist);

        return personRepository.save(pPersist);
    }

    public void delete(Long id) {
        Person pPersist = load(id);

        personRepository.delete(pPersist);
    }

    public void inactive(Long id) {
        Person pPersist = load(id);
        pPersist.setStatus(StatusEnum.INACTIVE);

        personRepository.save(pPersist);
    }

    public Page<Person> list(@PageableDefault(size = 5, sort = {"firstName"}) Pageable pageable) {
        return personRepository.findAll(pageable);
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
