package com.chatchatabc.jpademo.domain.service

import org.springframework.stereotype.Service

@Service
interface RoleService {

    /**
     * Create Roles on runtime
     */
    fun createRoles()
}