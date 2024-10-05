package com.jameswu.performance.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.ManyToOne
import java.io.Serializable

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "activity_tag_map")
data class ActivityTagMap(
    @EmbeddedId
    val id: ActivityTagId,

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id")
    val activity: Activity,

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    val tag: ActivityTag
){
    constructor(activity: Activity, tag: ActivityTag): this(ActivityTagId(activity.id!!, tag.id!!), activity, tag)
}

@Embeddable
data class ActivityTagId(
    @Column(name = "activity_id")
    val activityId: Int,

    @Column(name = "tag_id")
    val tagId: Int
) : Serializable
