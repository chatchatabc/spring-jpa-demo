package com.chatchatabc.jpademojava.domain.service;

import com.chatchatabc.jpademojava.domain.model.Passport;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface PassportService {

    /**
     * Create a passport for a user
     *
     * @param userId
     * @param passport
     * @return
     */
    User create(String userId, Passport passport) throws Exception;

    /**
     * Update a passport
     *
     * @param passportId
     * @param newPassportInfo
     * @return
     */
    Passport update(String passportId, Passport newPassportInfo) throws Exception;

    /**
     * Delete a passport
     *
     * @param userId
     * @return
     */
    User delete(String userId) throws Exception;
}
