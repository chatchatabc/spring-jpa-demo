package com.chatchatabc.jpademo.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Data
import java.time.Instant

@Data
@Entity
@Table(name = "passports")
open class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open lateinit var id: String

    @Column(unique = true)
    open lateinit var number: String

    @Column
    open lateinit var createdAt: Instant

    @Column
    open lateinit var updatedAt: Instant

    // Relationships
    @JsonIgnore
    @OneToOne(mappedBy = "passport", fetch = FetchType.LAZY)
    open lateinit var user: User

    @PrePersist
    fun prePersist() {
        createdAt = Instant.now()
        updatedAt = Instant.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }
}