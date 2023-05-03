package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.rest.BaseJson;
import com.salus.utils.SerializationUtils;
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
    public Person details(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDetails(id);

        return personService.load(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseJson create(@RequestBody String body) throws SalusException {
        BaseJson bjInput = SerializationUtils.deserializeBaseJson(body);

        controllerValidator.validateCreate(bjInput);

        Person p = personConverter.toModel(bjInput.getPerson(), null);

        p = personService.create(p);

        BaseJson bjOut = new BaseJson();
        embedCreateOnBaseJson(bjOut, p);
        return bjOut;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseJson update(@RequestBody String body, @PathVariable(value = "id") Long id) throws SalusException {
        BaseJson bjInput = SerializationUtils.deserializeBaseJson(body);

        controllerValidator.validateUpdate(bjInput, id);

        Person p = personConverter.toModel(bjInput.getPerson(), id);

        Person pNew = personService.update(p, id);

        BaseJson bjOut = new BaseJson();
        embedUpdateOnBaseJson(bjOut, pNew);
        return bjOut;
    }

    @DeleteMapping(value = "inactive/{id}")
    public void inactive(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateInactive(id);

        personService.inactive(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) throws SalusException {

        controllerValidator.validateDelete(id);

        personService.delete(id);
    }

    // PRIVATE METHODS
    private void embedCreateOnBaseJson(BaseJson bjOut, Person p) {
        PersonJson pj = personConverter.toJson(p);
        bjOut.setPerson(pj);
    }

    private void embedUpdateOnBaseJson(BaseJson baseJson, Person p) {
        PersonJson pj = personConverter.toJson(p);
        baseJson.setPerson(pj);
    }
}
