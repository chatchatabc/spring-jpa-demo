package com.chatchatabc.jpademo.domain.model

import jakarta.persistence.*
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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

    @CreationTimestamp
    open lateinit var createdAt: Instant

    @UpdateTimestamp
    open lateinit var updatedAt: Instant

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    open var user: User? = null
}