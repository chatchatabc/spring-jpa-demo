package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.domain.model.ROLE_NAMES
import com.chatchatabc.jpademo.domain.model.Role
import com.chatchatabc.jpademo.domain.repository.RoleRepository
import com.chatchatabc.jpademo.domain.service.RoleService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository
) : RoleService {

    private val log = LoggerFactory.getLogger(RoleServiceImpl::class.java)

    /**
     * Create Roles on runtime
     */
    override fun createRoles() {
        // Loop through ROLE_NAMES enum and create roles if they don't exist
        ROLE_NAMES.values().forEach { roleName ->
            val role = roleRepository.findRoleByName(roleName.toString())
            if (role.isEmpty) {
                roleRepository.save(Role(name = roleName.toString()))
                log.info("Role created: $roleName")
            }
        }
    }
}