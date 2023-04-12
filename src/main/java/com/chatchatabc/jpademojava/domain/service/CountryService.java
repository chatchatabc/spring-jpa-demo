package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {

    /**
     * Create country
     *
     * @param country
     * @return
     */
    Country create(Country country);

    /**
     * Update country
     *
     * @param countryId
     * @param newCountryInfo
     * @return
     */
    Country update(String countryId, Country newCountryInfo) throws Exception;

    /**
     * Assign user to country
     *
     * @param userId
     * @param countryId
     * @return
     */
    User assign(String userId, String countryId) throws Exception;

    /**
     * Unassign user from country
     *
     * @param userId
     * @return
     * @throws Exception
     */
    User unassign(String userId) throws Exception;
}
