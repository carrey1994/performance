package com.jameswu.performance.entity

import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDateTime

@Entity
@Table(name = "activities")
data class Activity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    val id: Int? = null,

    @Column(name = "activity_name", nullable = false, unique = true)
    val activityName: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "created_at")
    val createdAt: Instant
)
