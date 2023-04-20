package com.salus.person;

import com.salus.exception.SalusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private PersonControllerValidator controllerValidator;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person load(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateLoad(id);

        return personService.load(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody PersonJson body) throws SalusException {

        controllerValidator.validateCreate(body);
        Person p = personConverter.toModel(body, null);

        return personService.create(p);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody PersonJson body, @PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateUpdate(body, id);
        Person p = personConverter.toModel(body, id);

        return personService.update(p, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDelete(id);

        personService.delete(id);
    }
}
