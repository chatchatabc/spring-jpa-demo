package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.application.dto.country.CountryAssignRequest;
import com.chatchatabc.jpademojava.application.dto.country.CountryCreateRequest;
import com.chatchatabc.jpademojava.application.dto.country.CountryUpdateRequest;
import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.model.User;
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

    /**
     * Update country
     *
     * @param countryId
     * @param request
     * @return
     */
    Country update(String countryId, CountryUpdateRequest request) throws Exception;

    /**
     * Assign user to country
     *
     * @param request
     * @return
     */
    User assign(CountryAssignRequest request) throws Exception;
}
