package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.application.dto.country.CountryCreateRequest;
import com.chatchatabc.jpademojava.domain.model.Country;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {

    /**
     * Create country
     *
     * @param request
     * @return
     */
    Country create(CountryCreateRequest request);
}
