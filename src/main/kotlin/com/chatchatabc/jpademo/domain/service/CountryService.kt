package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.stereotype.Service

@Service
interface CountryService {
    /**
     * Create Country
     */
    fun create(country: Country): Country

    /**
     * Update Country
     */
    fun update(countryId: String, newCountryInfo: Country): Country

    /**
     * Assign Country to User
     */
    fun assign(userId: String, countryId: String): User

    /**
     * Unassign Country from User
     */
    fun unassign(userId: String): User
}