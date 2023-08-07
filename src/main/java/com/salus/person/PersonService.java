package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.person.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonServiceValidator validator;

    public Person load(Long id) {

        if (id == null) {
            return null;
        }

        return repository.findById(id).orElse(null);
    }

    public Person create(Person p) throws SalusException {

        setUpTimestamps(p);
        p.setStatus(StatusEnum.ACTIVE);

        validator.validateCreate(p);

        return repository.save(p);
    }

    public Person update(Person p, Long id) throws SalusException {
        validator.validateId(id);

        Person pPersist = load(id);

        setUpdate(p, pPersist);

        setUpTimestamps(pPersist);

        validator.validateUpdate(pPersist, id);

        return repository.save(pPersist);
    }

    public void delete(Long id) throws SalusException {
        validator.validateId(id);

        Person pPersist = load(id);

        repository.delete(pPersist);
    }

    public void inactivate(Long id) throws SalusException {
        validator.validateId(id);

        Person pPersist = load(id);
        pPersist.setStatus(StatusEnum.INACTIVE);

        repository.save(pPersist);
    }

    public Person details(Long id) throws SalusException {
        validator.validateId(id);

        return load(id);
    }

    public Page<Person> list(Pageable pageable) {
        return repository.findAll(pageable);
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
