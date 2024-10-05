package com.jameswu.performance.repository

import com.jameswu.performance.entity.Activity
import com.jameswu.performance.entity.ActivityTagId
import com.jameswu.performance.entity.ActivityTagMap
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.stream.Stream

interface ActivityTagMapRepository : JpaRepository<ActivityTagMap, ActivityTagId> {
    fun findByActivity(activity: Activity): List<ActivityTagMap>
}
