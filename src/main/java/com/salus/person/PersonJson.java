package com.salus.person;

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
        String created,
        String updated) {
}
