package com.jameswu.performance.api

import com.jameswu.performance.entity.UserTag
import com.jameswu.performance.repository.ActivityTagMapRepository
import com.jameswu.performance.repository.UserActivityLogRepository
import com.jameswu.performance.repository.UserTagRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/insert")
class InsertApi(
    val userActivityLogRepository: UserActivityLogRepository,
    val activityTagMapRepository: ActivityTagMapRepository,
    val userTagRepository: UserTagRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @PutMapping("/sql")
    @Transactional
    fun sql() {
        timer("sql") {
            userTagRepository.byNativeSQL()
        }
    }

    @PutMapping("/save-once")
    @Transactional
    fun all() {
        timer("all") {
            val logs = userActivityLogRepository.findAll()
            val activityTagCollection = activityTagMapRepository.findAll()
            val activityToTagCollection = activityTagCollection.groupBy { it.activity }
            val userTags = logs.flatMap { log ->
                activityToTagCollection[log.activity]?.map { UserTag(log.user, it.tag) } ?: emptyList()
            }
            userTagRepository.saveAll(userTags)
        }
    }

    @PutMapping("/save-one-by-one")
    @Transactional
    fun oneByOne() {
        val prev = Instant.now()
        println("oneByOne: starting insert: $prev")
        timer("save-one-by-one") {
            val activityTagCollection = activityTagMapRepository.findAll()
            val logs = userActivityLogRepository.streamAll()
            val activityToTagCollection = activityTagCollection.groupBy { it.activity }
            logs.forEach { log ->
                val activityTagMaps = activityToTagCollection[log.activity] ?: emptyList()
                if (activityTagMaps.isNotEmpty()) {
                    activityTagMaps.forEach {
                        userTagRepository.save(UserTag(log.user, it.tag))
                    }
                }
            }
        }
    }

    @PutMapping("/save-without-group-by")
    @Transactional
    fun noGroupBy() {
        timer("save-without-group-by") {
            val logs = userActivityLogRepository.findAll()
            val userTags = logs.flatMap { log ->
                activityTagMapRepository
                    .findByActivity(log.activity)
                    .map { UserTag(log.user, it.tag) }
            }
            userTagRepository.saveAll(userTags)
        }
    }

    private inline fun timer(name: String, block: () -> Unit) {
        val prev = Instant.now()
        logger.info("$name: starting insert: $prev")
        block()
        val now = Instant.now()
        logger.info("$name: finished insert: $now by ${now.toEpochMilli() - prev.toEpochMilli()}")
        logger.info("insert count: ${userTagRepository.count()}")
        userTagRepository.deleteAll()
    }

}