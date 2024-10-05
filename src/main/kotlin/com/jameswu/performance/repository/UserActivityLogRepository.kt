package com.jameswu.performance.repository

import com.jameswu.performance.entity.ActivityTagMap
import com.jameswu.performance.entity.UserActivityLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.stream.Stream

interface UserActivityLogRepository : JpaRepository<UserActivityLog, Int>{
    @Query("SELECT * FROM user_activity_logs", nativeQuery = true)
    fun streamAll(): Stream<UserActivityLog>
}
