package com.salus.person;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record PersonJson(
        Long id,
        String firstName,
        String lastName,
        String socialName,
        String gender,
        String birthday,
        String document,
        String value,
        String status,
        @JsonIgnore
        String created,
        @JsonIgnore
        String updated) {

}
