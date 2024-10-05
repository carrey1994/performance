package com.jameswu.performance.entity

import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDateTime

@Entity
@Table(name = "user_activity_logs")
data class UserActivityLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    val id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "activity_id")
    val activity: Activity,

    @Column(name = "participation_date")
    val participationDate: Instant
)
