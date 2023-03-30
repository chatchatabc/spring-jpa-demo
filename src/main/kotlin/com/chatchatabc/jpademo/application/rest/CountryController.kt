package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.country.CountryCreateRequest
import com.chatchatabc.jpademo.application.dto.country.CountryCreateResponse
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.repository.CountryRepository
import com.chatchatabc.jpademo.domain.service.CountryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/country")
class CountryController (
    private val countryRepository: CountryRepository,
    private val countryService: CountryService
) {

    /**
     * Get Countries
     */
    @GetMapping("/get")
    fun getCountries(
        pageable: Pageable
    ): ResponseEntity<Page<Country>> {
        return try {
            val countries = countryRepository.findAll(pageable)
            ResponseEntity.ok(countries)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    /**
     * Create Country
     */
    @PostMapping("/create")
    fun createCountry(
        @RequestBody request: CountryCreateRequest
    ): ResponseEntity<CountryCreateResponse> {
        return try {
            val country = countryService.create(request)
            ResponseEntity.ok(CountryCreateResponse(country, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest()
                .body(CountryCreateResponse(null, ErrorContent("Country Create Error", e.message)))
        }
    }

    // TODO: Update Country
}