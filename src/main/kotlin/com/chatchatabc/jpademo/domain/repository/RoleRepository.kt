package com.chatchatabc.jpademo.domain.repository

import com.chatchatabc.jpademo.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<Role, String> {
    /**
     * Find role by name
     */
    fun findRoleByName(name: String): Optional<Role>
}