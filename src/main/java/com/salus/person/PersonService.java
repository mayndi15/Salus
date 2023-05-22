package com.salus.person;

import com.salus.exception.SalusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonServiceValidator personServiceValidator;

    public Person load(Long id) {

        if (id == null) {
            return null;
        }

        return personRepository.getReferenceById(id);
    }

    public Person create(Person p) throws SalusException {

        setUpTimestamps(p);
        p.setStatus(StatusEnum.ACTIVE);

        personServiceValidator.validateCreate(p);

        return personRepository.save(p);
    }

    public Person update(Person p, Long id) throws SalusException {
        personServiceValidator.validateId(id);

        Person pPersist = load(id);

        setUpdate(p, pPersist);

        setUpTimestamps(pPersist);

        personServiceValidator.validateUpdate(p, id);

        return personRepository.save(pPersist);
    }

    public void delete(Long id) throws SalusException {
        personServiceValidator.validateDelete(id);

        Person pPersist = load(id);

        personRepository.delete(pPersist);
    }

    public void inactivate(Long id) throws SalusException {
        personServiceValidator.validateInactive(id);

        Person pPersist = load(id);
        pPersist.setStatus(StatusEnum.INACTIVE);

        personRepository.save(pPersist);
    }

    public Page<Person> list(Pageable pageable) {
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
