package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.application.dto.country.CountryAssignRequest
import com.chatchatabc.jpademo.application.dto.country.CountryCreateRequest
import com.chatchatabc.jpademo.application.dto.country.CountryUnassignRequest
import com.chatchatabc.jpademo.application.dto.country.CountryUpdateRequest
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.stereotype.Service

@Service
interface CountryService {
    /**
     * Create Country
     */
    fun create(country: CountryCreateRequest): Country

    /**
     * Update Country
     */
    fun update(countryId: String, request: CountryUpdateRequest): Country

    /**
     * Assign Country to User
     */
    fun assign(request: CountryAssignRequest): User

    /**
     * Unassign Country from User
     */
    fun unassign(request: CountryUnassignRequest): User
}