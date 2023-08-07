package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.person.validators.PersonServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonServiceValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenDetailsNullIdThenReturnAnNull() throws SalusException {
        Long id = null;

        var result = service.details(id);

        Mockito.verify(repository, Mockito.times(0)).findById(id);
        Mockito.verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNull(result);
    }

    @Test
    void whenDetailsIdNotExistsThenReturnAnNull() throws SalusException {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Long id = 1L;
        var result = service.details(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNull(result);
    }

    @Test
    void whenDetailsExistsIdThenReturnAnPerson() throws SalusException {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Person()));

        Long id = 1L;
        var result = service.details(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getClass(), Person.class);
    }
}
