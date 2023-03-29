package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.User;
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
}
