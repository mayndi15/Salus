package com.salus.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonJson {

    private Long id = 1L;
    private String firstName;
    private String lastName;
    private String socialName;
    private String gender;
    private String birthday;
    private String personType;
    private String value;
    private String created;
    private String updated;
}
