package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.stereotype.Service

@Service
interface PassportService {

    /**
     * Create Passport
     */
    fun create(userId: String, passport: Passport): User
}