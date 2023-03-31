package com.chatchatabc.jpademo.domain.model

import jakarta.persistence.*
import lombok.Data
import java.time.Instant

@Data
@Entity
@Table(name = "posts")
open class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open lateinit var id: String

    @Column
    open lateinit var title: String

    @Column
    open lateinit var content: String

    @Column
    open lateinit var createdAt: Instant

    @Column
    open lateinit var updatedAt: Instant

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    open var user: User? = null

    @PrePersist
    fun prePersist() {
        this.createdAt = Instant.now()
        this.updatedAt = Instant.now()
    }

    @PreUpdate
    fun preUpdate() {
        this.updatedAt = Instant.now()
    }
}