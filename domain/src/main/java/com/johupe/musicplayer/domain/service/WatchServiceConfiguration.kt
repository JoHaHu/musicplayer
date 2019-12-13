package com.johupe.musicplayer.domain.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.FileSystems

@Configuration
class WatchServiceConfiguration {

    @Bean
    fun watchService() = FileSystems.getDefault().newWatchService()!!

}
