package com.jameswu.performance.entity

import jakarta.persistence.*

@Entity
@Table(name = "activity_tags")
data class ActivityTag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    val id: Int? = null,

    @Column(name = "tag_name", nullable = false, unique = true)
    val tagName: String
)
