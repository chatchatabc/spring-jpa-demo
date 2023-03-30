package com.chatchatabc.jpademojava.application.rest;

import com.chatchatabc.jpademojava.domain.repository.CountryRepository;
import com.chatchatabc.jpademojava.domain.service.CountryService;
import com.chatchatabc.jpademojava.domain.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/get")
    public ResponseEntity<Page<Country>> getCountries(
            Pageable pageable
    ) {
        try {
            Page<Country> countries = countryRepository.findAll(pageable);
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
