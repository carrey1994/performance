package com.jameswu.performance.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "user_tags")
data class UserTag(
    @EmbeddedId
    val id: UserTagId,

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    val tag: ActivityTag
) {
    constructor(user: User, tag: ActivityTag) : this(UserTagId(user.id!!, tag.id!!), user, tag)
}

@Embeddable
data class UserTagId(
    @Column(name = "user_id")
    val userId: Int,

    @Column(name = "tag_id")
    val tagId: Int
) : Serializable