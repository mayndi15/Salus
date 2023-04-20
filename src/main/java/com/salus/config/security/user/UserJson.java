package com.salus.config.security.user;

import com.salus.person.PersonJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJson {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String role;

    private PersonJson person;
}
