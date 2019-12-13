package com.johupe.musicplayer.application.stages

import javafx.stage.Stage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class StageConfiguration {

    @Scope("prototype")
    @Bean
    fun stage(): Stage {
        return Stage()
    }
}
