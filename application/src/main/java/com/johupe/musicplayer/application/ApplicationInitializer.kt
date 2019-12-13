package com.johupe.musicplayer.application

import com.johupe.musicplayer.application.stages.MainStage
import com.johupe.musicplayer.core.events.FxApplicationReadyEvent
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationInitializer(
        private val mainStageProvider: ObjectProvider<MainStage>
) {

    @EventListener
    fun handleApplicationReadyEvent(event: FxApplicationReadyEvent) {
        val mainStage = mainStageProvider.getObject()
        mainStage.show()
    }
}
