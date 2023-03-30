package com.chatchatabc.jpademo.domain.service

import com.chatchatabc.jpademo.application.dto.country.CountryCreateRequest
import com.chatchatabc.jpademo.domain.model.Country
import org.springframework.stereotype.Service

@Service
interface CountryService {
    /**
     * Create Country
     */
    fun create(country: CountryCreateRequest): Country
}