package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.country.*
import com.chatchatabc.jpademo.domain.model.Country
import com.chatchatabc.jpademo.domain.model.User
import com.chatchatabc.jpademo.domain.repository.CountryRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.CountryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/country")
class CountryController(
    private val countryRepository: CountryRepository,
    private val countryService: CountryService,
    private val userRepository: UserRepository
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
     * Get Users by Country
     */
    @GetMapping("/get/{countryId}")
    fun getUsersByCountry(
        @PathVariable countryId: String,
        pageable: Pageable
    ): ResponseEntity<Page<User>> {
        return try {
            val country = countryRepository.findById(countryId)
            if (country.isEmpty) {
                throw Exception("Country not found")
            }
            val users = userRepository.findUsersByCountry(country.get(), pageable)
            ResponseEntity.ok(users)
        } catch (e: Exception) {
            e.printStackTrace()
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

    /**
     * Assign Country
     */
    @PutMapping("/assign")
    fun assignCountry(
        @RequestBody request: CountryAssignRequest
    ): ResponseEntity<CountryAssignResponse> {
        return try {
            val user = countryService.assign(request.userId, request.countryId)
            ResponseEntity.ok(CountryAssignResponse(user, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest()
                .body(CountryAssignResponse(null, ErrorContent("Country Update Error", e.message)))
        }
    }

    /**
     * Unassign Country
     */
    @PutMapping("/unassign")
    fun unassignCountry(
        @RequestBody request: CountryUnassignRequest
    ): ResponseEntity<CountryAssignResponse> {
        return try {
            val user = countryService.unassign(request.userId)
            ResponseEntity.ok(CountryAssignResponse(user, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest()
                .body(CountryAssignResponse(null, ErrorContent("Country Update Error", e.message)))
        }
    }

    /**
     * Delete Country
     */
    @DeleteMapping("/delete/{countryId}")
    fun deleteCountry(
        @PathVariable countryId: String
    ): ResponseEntity<String> {
        return try {
            countryRepository.deleteById(countryId)
            ResponseEntity.ok(null)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }
}