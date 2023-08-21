//package com.salus.person;
//
//import com.salus.exception.SalusException;
//import com.salus.person.enums.DocumentTypeEnum;
//import com.salus.person.enums.GenderEnum;
//import com.salus.person.enums.StatusEnum;
//import com.salus.person.validators.PersonServiceValidator;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.ZonedDateTime;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class PersonServiceTest {
//
//    @InjectMocks
//    private PersonService service;
//
//    @Mock
//    private PersonRepository repository;
//
//    @Mock
//    private PersonServiceValidator validator;
//
//    Person person = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//            "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//    Person personFirstNameNull = new Person(1L, null, "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//            "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void givenCreateWhenPersonValidThenReturnPerson() throws SalusException {
//        when(repository.save(person)).thenReturn(person);
//
//        Person result = service.create(person);
//
//        Assertions.assertEquals(result, person);
//
//        verify(repository, Mockito.times(1)).save(person);
//        verify(validator, Mockito.times(1)).validateCreate(person);
//    }
//
//
//    @Test(expected = SalusException.class)
//    public void givenCreateWhenPersonInvalidThenReturnException() throws SalusException {
//        //Mockito.doThrow(SalusException.class).doNothing().when(validator).validateCreate(personFirstNameNull);
//
//        Person result = service.create(personFirstNameNull);
//
//        Assertions.assertNotEquals(result, personFirstNameNull);
//
//        Mockito.verifyNoInteractions(repository.save(personFirstNameNull));
//        verify(validator, Mockito.times(1)).validateCreate(personFirstNameNull);
//    }
//
//    private void startPerson() {
//        Person personIdNull = new Person(null, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personFirstNameNull = new Person(1L, null, "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personLastNameNull = new Person(1L, "firstName", null, "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personGenderNull = new Person(1L, "firstName", "lastName", "socialName", null, LocalDate.now(), DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personBirthdayNull = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, null, DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personTypeDocumentNull = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), null,
//                "453.580.384-69", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personDocumentNull = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                null, StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personDocumentInvalid = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                "123.123.123-45", StatusEnum.ACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//
//        Person personStatusInactive = new Person(1L, "firstName", "lastName", "socialName", GenderEnum.OTHER, LocalDate.now(), DocumentTypeEnum.CPF,
//                "453.580.384-69", StatusEnum.INACTIVE, ZonedDateTime.now(), ZonedDateTime.now());
//    }
//}
