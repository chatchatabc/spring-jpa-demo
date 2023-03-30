package com.chatchatabc.jpademo.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Data
import java.time.Instant

@Data
@Entity
@Table(name = "countries")
open class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open lateinit var id: String

    @Column(unique = true)
    open lateinit var name: String

    @Column
    open lateinit var createdAt: Instant

    @Column
    open lateinit var updatedAt: Instant

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    open var users: MutableList<User> = mutableListOf()

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