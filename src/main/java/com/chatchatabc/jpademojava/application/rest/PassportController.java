package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.passport.*;
import com.chatchatabc.jpademojava.domain.model.Passport;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.PassportRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.PassportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/passport")
public class PassportController {
    @Autowired
    private PassportService passportService;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Get all passports
     *
     * @param pageable
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<Page<Passport>> getPassports(
            Pageable pageable
    ) {
        try {
            Page<Passport> passports = passportRepository.findAll(pageable);
            return ResponseEntity.ok(passports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get passport by user
     *
     * @param userId
     * @return
     */
    @GetMapping("/get/{userId}")
    public ResponseEntity<Passport> getPassportByUser(
            @PathVariable String userId
    ) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                throw new Exception("User not found");
            }
            Optional<Passport> passport = passportRepository.findPassportByUser(user.get());
            if (passport.isEmpty()) {
                throw new Exception("Passport not found");
            }
            return ResponseEntity.ok(passport.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create a passport for a user
     *
     * @param request
     * @param userId
     * @return
     */
    @PostMapping("/create/{userId}")
    public ResponseEntity<PassportResponse> createPassport(
            @RequestBody PassportCreateRequest request,
            @PathVariable String userId
    ) {
        try {
            Passport passport = modelMapper.map(request, Passport.class);
            User updatedUser = passportService.create(userId, passport);
            return ResponseEntity.ok(new PassportResponse(updatedUser, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new PassportResponse(null, new ErrorContent("Update Passport Error", e.getMessage())));
        }
    }

    /**
     * Update a passport
     *
     * @param passportId
     * @param request
     * @return
     */
    @PutMapping("/update/{passportId}")
    public ResponseEntity<PassportUpdateResponse> updatePassport(
            @PathVariable String passportId,
            @RequestBody PassportUpdateRequest request
    ) {
        try {
            Passport newPassportInfo = modelMapper.map(request, Passport.class);
            Passport updatedPassport = passportService.update(passportId, newPassportInfo);
            return ResponseEntity.ok(new PassportUpdateResponse(updatedPassport, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new PassportUpdateResponse(null, new ErrorContent("Update Passport Error", e.getMessage()))
            );
        }
    }

    /**
     * Delete a passport
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<PassportDeleteResponse> deletePassport(
            @PathVariable String userId
    ) {
        try {
            User updatedUser = passportService.delete(userId);
            return ResponseEntity.ok(new PassportDeleteResponse(updatedUser, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new PassportDeleteResponse(null, new ErrorContent("Delete Passport Error", e.getMessage()))
            );
        }
    }
}
