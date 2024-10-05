package com.jameswu.performance.repository

import com.jameswu.performance.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>
