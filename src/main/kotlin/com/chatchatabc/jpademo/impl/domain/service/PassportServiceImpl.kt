package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.PassportRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.PassportService
import org.springframework.stereotype.Service

@Service
class PassportServiceImpl(
    private val passportRepository: PassportRepository,
    private val userRepository: UserRepository
) : PassportService {
    /**
     * Create Passport
     */
    override fun create(userId: String, passport: Passport): User {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw Exception("User not found")
        }

        passport.user = user.get()
        val savedPassport = passportRepository.save(passport)

        // Save passport to user
        user.get().passport = savedPassport
        return userRepository.save(user.get())
    }

    /**
     * Delete Passport
     */
    override fun delete(userId: String): User {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw Exception("User not found")
        }
        val passport = passportRepository.findPassportByUser(user.get())
        if (passport.isEmpty) {
            throw Exception("Passport not found")
        }
        user.get().passport = null
        val savedUser = userRepository.save(user.get())
        passportRepository.delete(passport.get())
        return savedUser
    }
}