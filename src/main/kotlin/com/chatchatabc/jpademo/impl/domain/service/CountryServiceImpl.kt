package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.application.dto.country.CountryUpdateRequest
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.CountryRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.CountryService
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl (
    private val countryRepository: CountryRepository,
    private val userRepository: UserRepository
) : CountryService {

    /**
     * Create Country
     */
    override fun create(country: Country): Country {
        return countryRepository.save(country)
    }

    /**
     * Update Country
     */
    override fun update(countryId: String, request: CountryUpdateRequest): Country {
        val queriedCountry = countryRepository.findById(countryId)
        if (queriedCountry.isEmpty) {
            throw Exception("Country not found")
        }

        // Update fields
        queriedCountry.get().apply {
            name = request.name ?: name
        }
        return countryRepository.save(queriedCountry.get())
    }

    /**
     * Assign Country to User
     */
    override fun assign(userId: String, countryId: String): User {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw Exception("User not found")
        }
        val country = countryRepository.findById(countryId)
        if (country.isEmpty) {
            throw Exception("Country not found")
        }

        // Apply Update
        user.get().apply {
            this.country = country.get()
        }

        return userRepository.save(user.get())
    }

    /**
     * Unassign Country from User
     */
    override fun unassign(userId: String): User {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw Exception("User not found")
        }
        user.get().country = null
        return userRepository.save(user.get())
    }


}