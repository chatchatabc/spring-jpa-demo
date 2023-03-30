package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.application.dto.country.CountryCreateRequest
import com.chatchatabc.jpademo.application.dto.country.CountryUpdateRequest
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.repository.CountryRepository
import com.chatchatabc.jpademo.domain.service.CountryService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl (
    private val countryRepository: CountryRepository
) : CountryService {
    private val mapper = ModelMapper()

    /**
     * Create Country
     */
    override fun create(country: CountryCreateRequest): Country {
        val newCountry = mapper.map(country, Country::class.java)
        return countryRepository.save(newCountry)
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
}