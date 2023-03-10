package com.salus.config.security.user;

import com.salus.person.PersonJson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
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
