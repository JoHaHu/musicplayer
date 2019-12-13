package com.johupe.musicplayer.core.events

import javafx.application.Application
import org.springframework.context.ApplicationEvent

class FxApplicationReadyEvent(application: Application) : ApplicationEvent(application) {
    val application = source as Application
}
