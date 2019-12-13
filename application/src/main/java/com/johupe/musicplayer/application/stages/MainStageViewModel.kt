package com.johupe.musicplayer.application.stages

import com.johupe.musicplayer.application.ViewModel
import com.johupe.musicplayer.domain.service.MusicPlayerService
import org.springframework.stereotype.Component

@Component
class MainStageViewModel(val musicPlayerService: MusicPlayerService) : ViewModel<MainStage>


