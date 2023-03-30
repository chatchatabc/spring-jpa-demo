package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.CountryRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.CountryService;
import com.chatchatabc.jpademojava.domain.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
