package com.chatchatabc.jpademojava.infra;

import com.chatchatabc.jpademojava.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInit implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    /**
     * Create roles on runtime
     *
     * @param args
     * @throws Exception
     */
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        roleService.createRoles();
    }
}
