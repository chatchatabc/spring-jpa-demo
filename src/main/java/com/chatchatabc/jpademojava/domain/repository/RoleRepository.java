package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    /**
     * Find role by name
     *
     * @param name
     * @return
     */
    Optional<Role> findRoleByName(String name);
}
