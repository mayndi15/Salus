package com.salus.config.security.authentication;

import com.salus.config.security.user.UserJson;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationJson> register(@RequestBody UserJson uj){
        return ResponseEntity.ok(authenticationService.register(uj));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationJson> authenticate(@RequestBody UserJson uj){
        return ResponseEntity.ok(authenticationService.authenticate(uj));
    }
}
