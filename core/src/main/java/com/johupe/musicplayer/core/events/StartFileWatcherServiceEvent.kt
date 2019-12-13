package com.johupe.musicplayer.core.events

import javafx.application.Application
import org.springframework.context.ApplicationEvent

class StartFileWatcherServiceEvent(app: Application) : ApplicationEvent(app) {

    val application = source as Application
}
