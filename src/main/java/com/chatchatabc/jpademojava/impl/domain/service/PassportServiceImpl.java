package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.domain.model.Passport;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.PassportRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassportRepository passportRepository;

    /**
     * Create a passport for a user
     *
     * @param userId
     * @param passport
     * @return
     */
    @Override
    public User create(String userId, Passport passport) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }

        passport.setUser(user.get());
        Passport savedPassport = passportRepository.save(passport);

        // Save passport to user
        user.get().setPassport(savedPassport);
        return userRepository.save(user.get());
    }

    /**
     * Update a passport
     *
     * @param passportId
     * @param newPassportInfo
     * @return
     */
    @Override
    public Passport update(String passportId, Passport newPassportInfo) throws Exception {
        Optional<Passport> passport = passportRepository.findById(passportId);
        if (passport.isEmpty()) {
            throw new Exception("Passport not found");
        }

        // Update fields
        if (newPassportInfo.getNumber() != null) {
            passport.get().setNumber(newPassportInfo.getNumber());
        }

        return passportRepository.save(passport.get());
    }

    /**
     * Delete a passport
     *
     * @param userId
     * @return
     */
    @Override
    public User delete(String userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
           throw new Exception("User not found");
        }
        Optional<Passport> passport = passportRepository.findPassportByUser(user.get());
        if (passport.isEmpty()) {
            throw new Exception("Passport not found");
        }
        user.get().setPassport(null);
        User savedUser = userRepository.save(user.get());
        passportRepository.delete(passport.get());
        return savedUser;
    }
}
