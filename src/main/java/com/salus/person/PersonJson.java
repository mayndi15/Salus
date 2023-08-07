package com.salus.person;

import java.io.Serializable;

public record PersonJson(

        Long id,
        String firstName,
        String lastName,
        String socialName,
        String gender,
        String birthday,
        String typeDocument,
        String document,
        String status,
        String created,
        String updated) implements Serializable {
}
