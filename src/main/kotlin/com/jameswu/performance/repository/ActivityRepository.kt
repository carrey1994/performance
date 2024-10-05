package com.jameswu.performance.repository

import com.jameswu.performance.entity.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Int>
