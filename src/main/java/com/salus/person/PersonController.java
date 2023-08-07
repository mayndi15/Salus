package com.salus.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.salus.exception.SalusException;
import com.salus.rest.BaseJson;
import com.salus.utils.JsonUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> create(@RequestBody String body) throws SalusException, JsonProcessingException {
        BaseJson request = JsonUtils.deserialize(body, BaseJson.class);

        controllerValidator.validateCreate(request);

        Person p = personConverter.toModel(request.getPerson(), null);

        p = personService.create(p);

        PersonJson response = embedJson(p);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> update(@RequestBody String body, @PathVariable(value = "id") Long id) throws SalusException, JsonProcessingException {
        BaseJson request = JsonUtils.deserialize(body, BaseJson.class);

        controllerValidator.validateUpdate(request, id);

        Person p = personConverter.toModel(request.getPerson(), id);

        Person pNew = personService.update(p, id);

        PersonJson response = embedJson(pNew);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> details(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDetails(id);

        Person p = personService.details(id);

        PersonJson response = embedJson(p);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Person>> list(Pageable pageable) throws SalusException {

        Page<Person> pList = personService.list(pageable);

        return ResponseEntity.ok(pList);
    }

    @DeleteMapping(value = "inactivate/{id}")
    public ResponseEntity inactivate(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateInactive(id);

        personService.inactivate(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDelete(id);

        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // PRIVATE METHODS
    private PersonJson embedJson(Person p) {

        return personConverter.toJson(p);
    }
}
