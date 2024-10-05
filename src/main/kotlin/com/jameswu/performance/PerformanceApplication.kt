package com.jameswu.performance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PerformanceApplication

fun main(args: Array<String>) {
	runApplication<PerformanceApplication>(*args)
}
