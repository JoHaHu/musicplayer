package com.johupe.musicplayer.domain.service

import com.johupe.musicplayer.domain.model.media.Playlist
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MusicPlayerServiceConfiguration {


    @Bean
    fun musicPlayerService(playlist: Playlist): MusicPlayerService = MusicPlayerServiceImpl(playlist)

    @Bean
    fun playlist(): Playlist = Playlist()


}
