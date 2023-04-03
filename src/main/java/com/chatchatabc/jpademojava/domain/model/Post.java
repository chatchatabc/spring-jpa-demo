package com.chatchatabc.jpademojava.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
