package com.chatchatabc.jpademo.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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

    @CreationTimestamp
    open lateinit var createdAt: Instant

    @UpdateTimestamp
    open lateinit var updatedAt: Instant

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    open var users: MutableList<User> = mutableListOf()
}