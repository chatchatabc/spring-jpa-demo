package com.chatchatabc.jpademo.domain.repository

import com.chatchatabc.jpademo.domain.model.Post
import com.chatchatabc.jpademo.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface PostRepository : JpaRepository<Post, String> {

    /**
     * Find all posts between date range
     */
    @Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    fun findAllBetweenDateRange(startDate: Instant?, endDate: Instant?, pageable: Pageable): Page<Post>

    /**
     * Find all posts between date range and by user param optionally if not null.
     */
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.createdAt BETWEEN :startDate AND :endDate")
    fun findAllBetweenDateRangeByUser(startDate: Instant?, endDate: Instant?, user: User, pageable: Pageable): Page<Post>
}