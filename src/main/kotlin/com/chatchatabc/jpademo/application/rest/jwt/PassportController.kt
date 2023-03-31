package com.chatchatabc.jpademo.application.rest.jwt

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.passport.PassportCreateRequest
import com.chatchatabc.jpademo.application.dto.passport.PassportCreateResponse
import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.repository.PassportRepository
import com.chatchatabc.jpademo.domain.repository.UserRepository
import com.chatchatabc.jpademo.domain.service.PassportService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/passport")
class PassportController(
    private val passportService: PassportService,
    private val passportRepository: PassportRepository,
    private val userRepository: UserRepository
) {
    private val mapper = ModelMapper()

    @GetMapping("/get")
    fun getPassports(
        pageable: Pageable
    ): ResponseEntity<Page<Passport>> {
        return try {
            val passports = passportRepository.findAll(pageable)
            ResponseEntity.ok(passports)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    /**
     * Get Passport by User
     */
    @GetMapping("/get/{userId}")
    fun getPassport(
        @PathVariable userId: String
    ): ResponseEntity<Passport> {
        return try {
            val user = userRepository.findById(userId)
            val passport = passportRepository.findPassportByUser(user.get())
            ResponseEntity.ok(passport.get())
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    /**
     * Create Passport
     */
    @PostMapping("/create/{userId}")
    fun createPassport(
        @RequestBody request: PassportCreateRequest,
        @PathVariable userId: String
    ): ResponseEntity<PassportCreateResponse> {
        return try {
            val passport = mapper.map(request, Passport::class.java)
            val createdPassport = passportService.create(userId, passport)
            ResponseEntity.ok(PassportCreateResponse(createdPassport, null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                PassportCreateResponse(
                    null, ErrorContent(
                        "Create Passport Error", e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }
}