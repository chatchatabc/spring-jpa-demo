package com.chatchatabc.jpademo.infra

import com.chatchatabc.jpademo.domain.service.RoleService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataInit(
    private val roleService: RoleService
) : CommandLineRunner {

    /**
     * Create Roles on runtime
     */
    @Transactional
    override fun run(vararg args: String?) {
        roleService.createRoles()
    }
}