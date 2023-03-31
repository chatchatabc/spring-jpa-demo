package com.chatchatabc.jpademo.application.rest.jwt

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.passport.PassportCreateRequest
import com.chatchatabc.jpademo.application.dto.passport.PassportCreateResponse
import com.chatchatabc.jpademo.domain.model.Passport
import com.chatchatabc.jpademo.domain.repository.PassportRepository
import com.chatchatabc.jpademo.domain.service.PassportService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/passport")
class PassportController(
    private val passportService: PassportService,
    private val passportRepository: PassportRepository
) {
    private val mapper = ModelMapper()

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