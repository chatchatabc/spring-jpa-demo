package com.chatchatabc.jpademo.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Data
@Entity
@Table(name = "users")
open class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    open lateinit var id: String

    @Column(unique = true)
    open lateinit var email: String

    @Column(unique = true)
    private lateinit var username: String

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private lateinit var password: String

    @Column
    open lateinit var createdAt: Instant

    @Column
    open lateinit var updatedAt: Instant

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // return empty list
        return mutableListOf()
    }

    override fun getPassword(): String {
        return this.password
    }

    open fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername(): String {
        return this.username
    }

    open fun setUsername(username: String) {
        this.username = username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

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