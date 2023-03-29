package com.chatchatabc.jpademo.domain.repository

import com.chatchatabc.jpademo.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {

    /**
     * Find user by username
     */
    fun findByUsername(username: String): Optional<User>
}