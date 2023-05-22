package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.rest.BaseJson;
import com.salus.utils.SerializationUtils;
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
    public ResponseEntity<BaseJson> create(@RequestBody String body) throws SalusException {
        BaseJson bjInput = SerializationUtils.deserializeBaseJson(body);

        controllerValidator.validateCreate(bjInput);

        Person p = personConverter.toModel(bjInput.getPerson(), null);

        p = personService.create(p);

        BaseJson bjOut = new BaseJson();
        embedOnBaseJson(bjOut, p);
        return ResponseEntity.ok(bjOut);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseJson> update(@RequestBody String body, @PathVariable(value = "id") Long id) throws SalusException {
        BaseJson bjInput = SerializationUtils.deserializeBaseJson(body);

        controllerValidator.validateUpdate(bjInput, id);

        Person p = personConverter.toModel(bjInput.getPerson(), id);

        Person pNew = personService.update(p, id);

        BaseJson bjOut = new BaseJson();
        embedOnBaseJson(bjOut, pNew);
        return ResponseEntity.ok(bjOut);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseJson> details(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDetails(id);

        Person p = personService.load(id);

        BaseJson bjOut = new BaseJson();
        embedOnBaseJson(bjOut, p);
        return ResponseEntity.ok(bjOut);
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
    private void embedOnBaseJson(BaseJson bjOut, Person p) {
        PersonJson pj = personConverter.toJson(p);
        bjOut.setPerson(pj);
    }
}
