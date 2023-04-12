package com.chatchatabc.jpademojava.domain.repository;

import com.chatchatabc.jpademojava.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    /**
     * Find all posts between date range
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    Page<Post> findAllBetweenDateRange(Instant startDate, Instant endDate, Pageable pageable);

    /**
     * Find all posts between date range by user
     *
     * @param startDate
     * @param endDate
     * @param userId
     * @return
     */
    @Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDate AND :endDate AND p.user.id = :userId")
    Page<Post> findAllBetweenDateRangeByUser(Instant startDate, Instant endDate, String userId, Pageable pageable);
}
