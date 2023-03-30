package com.chatchatabc.jpademojava.impl.domain.service;

import com.chatchatabc.jpademojava.application.dto.country.CountryCreateRequest;
import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.repository.CountryRepository;
import com.chatchatabc.jpademojava.domain.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

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
}
