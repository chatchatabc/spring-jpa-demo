package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.application.dto.country.CountryCreateRequest;
import com.chatchatabc.jpademojava.application.dto.country.CountryUpdateRequest;
import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.model.User;
import com.chatchatabc.jpademojava.domain.repository.CountryRepository;
import com.chatchatabc.jpademojava.domain.repository.UserRepository;
import com.chatchatabc.jpademojava.domain.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    /**
     * Create country
     *
     * @param request
     * @return
     */
    @Override
    public Country create(CountryCreateRequest request) {
        Country newCountry = mapper.map(request, Country.class);
        return countryRepository.save(newCountry);
    }

    /**
     * Update country
     *
     * @param countryId
     * @param request
     * @return
     */
    @Override
    public Country update(String countryId, CountryUpdateRequest request) throws Exception {
        Optional<Country> queriedCountry = countryRepository.findById(countryId);
        if (queriedCountry.isEmpty()) {
            throw new Exception("Country not found");
        }

        // Update Fields
        if (request.getName() != null) {
            queriedCountry.get().setName(request.getName());
        }

        return countryRepository.save(queriedCountry.get());
    }

    /**
     * Assign user to country
     *
     * @param userId
     * @param countryId
     * @return
     */
    @Override
    public User assign(String userId, String countryId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        Optional<Country> country = countryRepository.findById(countryId);
        if (country.isEmpty()) {
            throw new Exception("Country not found");
        }

        // Apply Update
        user.get().setCountry(country.get());

        return userRepository.save(user.get());
    }
}
