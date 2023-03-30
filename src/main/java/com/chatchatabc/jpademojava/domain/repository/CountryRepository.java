package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
