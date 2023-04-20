package com.salus.config.security.authentication;

import com.salus.config.security.jwt.JwtService;
import com.salus.config.security.role.Role;
import com.salus.config.security.role.RoleEnum;
import com.salus.config.security.role.RoleService;
import com.salus.config.security.user.User;
import com.salus.config.security.user.UserJson;
import com.salus.config.security.user.UserRepository;
import com.salus.person.Person;
import com.salus.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    public AuthenticationJson register(UserJson uj) {
        Person person = personService.load(uj.getPerson().getId());
        Role role = roleService.findByName("ADMIN");
        var user = User.builder()
                .username(uj.getUsername())
                .password(passwordEncoder.encode(uj.getPassword()))
                .roles(List.of(role))
                .person(person)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationJson.builder().token(jwtToken).build();
    }

    public AuthenticationJson authenticate(UserJson uj) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        uj.getUsername(),
                        uj.getPassword()
                )
        );

        var user = userRepository.findByUsername(uj.getUsername());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationJson.builder().token(jwtToken).build();
    }
}
