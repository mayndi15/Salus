package com.salus.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salus.person.PersonJson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private PersonJson person;
}
