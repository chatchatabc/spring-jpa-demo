package com.chatchatabc.jpademo.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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

    @CreationTimestamp
    open lateinit var createdAt: Instant

    @UpdateTimestamp
    open lateinit var updatedAt: Instant

    // Relationships
    @JsonIgnore
    @OneToOne(mappedBy = "passport", fetch = FetchType.LAZY)
    open lateinit var user: User
}