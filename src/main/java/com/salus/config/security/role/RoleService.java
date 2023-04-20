package com.salus.config.security.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {

        Role rolePersist = roleRepository.findByName(name);

        return rolePersist;
    }

}
