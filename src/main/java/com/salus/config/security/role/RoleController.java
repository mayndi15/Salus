package com.salus.config.security.role;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@Tag(name = "Role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Role load(@RequestBody String name) {

        return roleService.findByName(name);
    }
}
