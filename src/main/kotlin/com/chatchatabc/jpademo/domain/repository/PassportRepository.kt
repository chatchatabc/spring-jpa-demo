package com.chatchatabc.jpademo.domain.repository

import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PassportRepository : JpaRepository<Passport, String> {

    /**
     * Find Passport by User
     */
    @Query("SELECT p FROM Passport p WHERE p.user = :user")
    fun findPassportByUser(user: User): Optional<Passport>
}