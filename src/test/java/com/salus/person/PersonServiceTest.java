package com.salus.person;

import com.salus.exception.SalusException;
import com.salus.person.validators.PersonServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void whenDeleteNullIdThenReturnAnNull() throws SalusException {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Long id = null;
        service.delete(id);

        verify(repository, Mockito.times(1)).delete(null);
        verify(validator, Mockito.times(1)).validateId(id);
    }

    @Test
    void whenDeleteIdNotExistsThenReturnAnNull() throws SalusException {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Long id = 1L;
        service.delete(id);

        verify(repository, Mockito.times(1)).delete(null);
        verify(validator, Mockito.times(1)).validateId(id);
    }

    @Test
    void whenDeleteExistsIdThenReturnAnPerson() throws SalusException {
        Person person = new Person();
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(person));
        doNothing().when(repository).delete(person);

        Long id = 1L;
        service.delete(id);

        verify(repository, Mockito.times(1)).delete(person);
        verify(validator, Mockito.times(1)).validateId(id);
    }

    @Test
    void whenDetailsNullIdThenReturnAnNull() throws SalusException {
        Long id = null;
        var result = service.details(id);

        verify(repository, Mockito.times(0)).findById(id);
        verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNull(result);
    }

    @Test
    void whenDetailsIdNotExistsThenReturnAnNull() throws SalusException {
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        Long id = 1L;
        var result = service.details(id);

        verify(repository, Mockito.times(1)).findById(id);
        verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNull(result);
    }

    @Test
    void whenDetailsExistsIdThenReturnAnPerson() throws SalusException {
        Person person = new Person();
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(person));

        Long id = 1L;
        var result = service.details(id);

        verify(repository, Mockito.times(1)).findById(id);
        verify(validator, Mockito.times(1)).validateId(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getClass(), Person.class);
    }
}
