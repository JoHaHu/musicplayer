package com.johupe.musicplayer.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AdviceMode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.aspectj.EnableSpringConfigured
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor

@SpringBootApplication
class AppConfig {

    @Bean
    fun threadPoolTaskExecutor() = SimpleThreadPoolTaskExecutor().also {
        it.threadCount = Runtime.getRuntime().availableProcessors()
        it.threadNamePrefix = "Async_Worker"
    }

}
