package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.Country;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Find user by username
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * Find users by country
     *
     * @param country
     * @param pageable
     * @return
     */
    Page<User> findUsersByCountry(Country country, Pageable pageable);
}
