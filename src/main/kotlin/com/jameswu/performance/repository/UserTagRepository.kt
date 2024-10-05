package com.jameswu.performance.repository

import com.jameswu.performance.entity.UserTag
import com.jameswu.performance.entity.UserTagId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserTagRepository : JpaRepository<UserTag, UserTagId>{

    @Modifying
    @Query("""
        WITH user_activity_tags AS (
            SELECT
                ual.user_id,
                at.tag_id
            FROM
                user_activity_logs ual
                JOIN activity_tag_map at ON ual.activity_id = at.activity_id
        ) INSERT INTO user_tags (user_id, tag_id)
        SELECT DISTINCT
            uat.user_id,
            uat.tag_id
        FROM
            user_activity_tags uat;
    """, nativeQuery = true)
    fun byNativeSQL()
}

