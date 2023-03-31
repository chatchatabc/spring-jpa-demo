package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.Passport;
import com.chatchatabc.jpademojava.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {

    /**
     * Find passport by user
     *
     * @param user
     * @return
     */
    @Query("SELECT p FROM Passport p WHERE p.user = :user")
    Optional<Passport> findPassportByUser(User user);
}
