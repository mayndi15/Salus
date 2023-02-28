package com.salus.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "social_name")
    private String socialName;

    @Column(name = "gender", nullable = false)
    private GenderEnum gender;

    @Column(name = "birthday", nullable = false)
    private ZonedDateTime birthday;

    @Column(name = "person_type", nullable = false)
    private PersonTypeEnum personType = PersonTypeEnum.CPF;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "created", nullable = false, columnDefinition = "timestamp with time zone")
    private ZonedDateTime created;

    @Column(name = "updated", nullable = false, columnDefinition = "timestamp with time zone")
    private ZonedDateTime updated;
}