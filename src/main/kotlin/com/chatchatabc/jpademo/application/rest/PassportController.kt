package com.chatchatabc.jpademo.application.rest

import com.chatchatabc.jpademo.application.dto.ErrorContent
import com.chatchatabc.jpademo.application.dto.passport.*
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

    /**
     * Get Passports
     */
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
    ): ResponseEntity<PassportResponse> {
        return try {
            val passport = mapper.map(request, Passport::class.java)
            val updatedUser = passportService.create(userId, passport)
            ResponseEntity.ok(PassportResponse(updatedUser, null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                PassportResponse(
                    null, ErrorContent(
                        "Create Passport Error", e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }

    /**
     * Update Passport
     */
    @PutMapping("/update/{passportId}")
    fun updatePassport(
        @PathVariable passportId: String,
        @RequestBody request: PassportUpdateRequest
    ): ResponseEntity<PassportUpdateResponse> {
        return try {
            val newPassportInfo = mapper.map(request, Passport::class.java)
            val updatedPassport = passportService.update(passportId, newPassportInfo)
            ResponseEntity.ok(PassportUpdateResponse(updatedPassport, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(
                PassportUpdateResponse(
                    null, ErrorContent(
                        "Update Passport Error", e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }

    /**
     * Delete Passport
     */
    @DeleteMapping("/delete/{userId}")
    fun deletePassport(
        @PathVariable userId: String
    ): ResponseEntity<PassportDeleteResponse> {
        return try {
            val user = passportService.delete(userId)
            ResponseEntity.ok(PassportDeleteResponse(user, null))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(
                PassportDeleteResponse(
                    null, ErrorContent(
                        "Delete Passport Error", e.message ?: "Unknown Error"
                    )
                )
            )
        }
    }
}