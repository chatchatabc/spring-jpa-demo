package com.chatchatabc.jpademo.domain.repository

import com.chatchatabc.jpademo.domain.model.Passport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PassportRepository : JpaRepository<Passport, String> {
}