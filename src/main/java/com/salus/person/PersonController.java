package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.person.validators.PersonControllerValidator;
import com.salus.rest.BaseJson;
import com.salus.utils.JsonUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @Autowired
    private PersonControllerValidator validator;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> create(@RequestBody String body) throws SalusException {
        BaseJson request = JsonUtils.deserialize(body, BaseJson.class);

        validator.validateCreate(request);

        Person p = converter.toModel(request.getPerson(), null);

        p = service.create(p);

        PersonJson response = embedJson(p);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> update(@RequestBody String body, @PathVariable(value = "id") Long id) throws SalusException {
        BaseJson request = JsonUtils.deserialize(body, BaseJson.class);

        validator.validateUpdate(request, id);

        Person p = converter.toModel(request.getPerson(), id);

        Person pNew = service.update(p, id);

        PersonJson response = embedJson(pNew);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> details(@PathVariable(value = "id") Long id) throws SalusException {

        validator.validateDetails(id);

        Person p = service.details(id);

        PersonJson response = embedJson(p);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Person>> list(Pageable pageable) {

        Page<Person> pList = service.list(pageable);

        return ResponseEntity.ok(pList);
    }

    @DeleteMapping(value = "inactivate/{id}")
    public ResponseEntity inactivate(@PathVariable(value = "id") Long id) throws SalusException {

        validator.validateInactive(id);

        service.inactivate(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) throws SalusException {

        validator.validateDelete(id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "findByDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonJson> findByDocument(@RequestBody String body) throws SalusException {
        BaseJson request = JsonUtils.deserialize(body, BaseJson.class);

        String document = request.getPerson().document();

        Person p = service.findByDocument(document);

        PersonJson response = embedJson(p);
        return ResponseEntity.ok(response);
    }

    // PRIVATE METHODS
    private PersonJson embedJson(Person p) {

        return converter.toJson(p);
    }
}
