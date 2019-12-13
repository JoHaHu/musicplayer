package com.johupe.musicplayer.application.stages

import javafx.event.Event
import javafx.event.EventTarget
import javafx.event.EventType
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.WindowEvent

abstract class BaseStage(protected val stage: Stage) : EventTarget by stage {

    protected abstract val root: Parent
    protected val scene by lazy { Scene(root) }

    open fun show() {
        stage.scene = scene
        stage.setOnCloseRequest(this::close)
        stage.show()
    }

    open fun close(e: WindowEvent) {
        stage.close()
    }

    fun <T : Event> addEventHandler(eventType: EventType<T>, handler: (T) -> Unit) {
        stage.addEventHandler(eventType, handler)
    }

    fun <T : Event> addEventFilter(eventType: EventType<T>, handler: (T) -> Unit) {
        stage.addEventFilter(eventType, handler)
    }

    fun <T : Event> removeEventHandler(eventType: EventType<T>, handler: (T) -> Unit) {
        stage.removeEventHandler(eventType, handler)
    }

    fun <T : Event> removeEventFilter(eventType: EventType<T>, handler: (T) -> Unit) {
        stage.removeEventFilter(eventType, handler)
    }


}
