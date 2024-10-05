package com.jameswu.performance.entity

import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Int? = null,

    @Column(name = "username", nullable = false, unique = true)
    val username: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "created_at")
    val createdAt: Instant
)