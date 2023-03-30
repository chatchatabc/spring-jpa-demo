package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.country.*
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.repository.CountryRepository
import com.chatchatabc.jpademo.domain.service.CountryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    /**
     * Update Country
     */
    @PutMapping("/update/{countryId}")
    fun updateCountry(
        @PathVariable countryId: String,
        @RequestBody request: CountryUpdateRequest
    ): ResponseEntity<CountryUpdateResponse> {
        return try {
            val country = countryService.update(countryId, request)
            ResponseEntity.ok(CountryUpdateResponse(country, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest()
                .body(CountryUpdateResponse(null, ErrorContent("Country Update Error", e.message)))
        }
    }

    @PutMapping("/assign")
    fun assignCountry(
        @RequestBody request: CountryAssignRequest
    ): ResponseEntity<CountryAssignResponse> {
        return try {
            val user = countryService.assign(request)
            ResponseEntity.ok(CountryAssignResponse(user, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest()
                .body(CountryAssignResponse(null, ErrorContent("Country Update Error", e.message)))
        }
    }
}