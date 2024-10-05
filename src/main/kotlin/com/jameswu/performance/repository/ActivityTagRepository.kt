package com.jameswu.performance.repository

import com.jameswu.performance.entity.ActivityTag
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityTagRepository: JpaRepository<ActivityTag, Int>