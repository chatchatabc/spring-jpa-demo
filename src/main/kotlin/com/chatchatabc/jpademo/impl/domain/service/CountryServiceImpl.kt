package com.chatchatabc.jpademo.impl.domain.service

import com.chatchatabc.jpademo.application.dto.country.CountryCreateRequest
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
}