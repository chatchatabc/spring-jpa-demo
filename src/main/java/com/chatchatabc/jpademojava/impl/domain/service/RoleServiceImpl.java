package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.domain.enums.ROLE_NAMES;
import com.chatchatabc.jpademojava.domain.model.Role;
import com.chatchatabc.jpademojava.domain.repository.RoleRepository;
import com.chatchatabc.jpademojava.domain.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    /**
     * Create roles
     */
    @Override
    public void createRoles() {
        // Loop through ROLE_NAMES enum and create roles if they don't exist
        for (ROLE_NAMES role : ROLE_NAMES.values()) {
            if (roleRepository.findRoleByName(role.toString()).isEmpty()) {
                roleRepository.save(
                        Role.builder()
                                .name(role.name())
                                .build());
                log.info("Role created: {}", role.name());
            }
        }
    }
}
