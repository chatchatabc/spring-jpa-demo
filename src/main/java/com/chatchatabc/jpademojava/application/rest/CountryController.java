package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.application.dto.ErrorContent;
import com.chatchatabc.jpademojava.application.dto.country.*;
import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.CountryRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    /**
     * Get countries
     *
     * @param pageable
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<Page<Country>> getCountries(
            Pageable pageable
    ) {
        try {
            Page<Country> countries = countryRepository.findAll(pageable);
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Get users by country
     *
     * @param countryId
     * @param pageable
     * @return
     */
    @GetMapping("/get/{countryId}")
    public ResponseEntity<Page<User>> getUsersByCountry(
            @PathVariable String countryId,
            Pageable pageable
    ) {
        try {
            Optional<Country> country = countryRepository.findById(countryId);
            if (country.isEmpty()) {
                throw new Exception("Country not found");
            }
            Page<User> users = userRepository.findUsersByCountry(country.get(), pageable);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Create country
     *
     * @param request
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<CountryCreateResponse> createCountry(
            @RequestBody CountryCreateRequest request
    ) {
        try {
            Country newCountry = mapper.map(request, Country.class);
            Country country = countryService.create(newCountry);
            return ResponseEntity.ok(new CountryCreateResponse(country, null));
        } catch (Exception e) {
            // TODO: Improve error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CountryCreateResponse(null, new ErrorContent("Create Country Error", e.getMessage()))
            );
        }
    }

    /**
     * Update country
     *
     * @param countryId
     * @param request
     * @return
     */
    @PutMapping("/update/{countryId}")
    public ResponseEntity<CountryUpdateResponse> updateCountry(
            @PathVariable String countryId,
            @RequestBody CountryUpdateRequest request
    ) {
        try {
            Country newCountryInfo = mapper.map(request, Country.class);
            Country country = countryService.update(countryId, newCountryInfo);
            return ResponseEntity.ok(new CountryUpdateResponse(country, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CountryUpdateResponse(null, new ErrorContent("Update Country Error", e.getMessage()))
            );
        }
    }

    @PutMapping("/assign")
    public ResponseEntity<CountryAssignResponse> assignCountry(
            @RequestBody CountryAssignRequest request
    ) {
        try {
            User user = countryService.assign(request.getUserId(), request.getCountryId());
            return ResponseEntity.ok(new CountryAssignResponse(user, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CountryAssignResponse(null, new ErrorContent("Assign Country Error", e.getMessage()))
            );
        }
    }
}