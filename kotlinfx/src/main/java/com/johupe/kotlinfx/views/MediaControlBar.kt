package com.johupe.kotlinfx.views

import com.johupe.kotlinfx.DSL
import com.johupe.kotlinfx.button
import com.johupe.kotlinfx.tryAttach
import javafx.event.EventTarget
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox


class MediaControlBar : HBox() {

    private val prevButton: Button
    private val controlButton: Button
    private val nextButton: Button

    init {
        alignment = Pos.CENTER
        prevButton = button("prev")
        controlButton = button("Play")
        nextButton = button("next")
    }
}

inline fun EventTarget.mediacontrolbar(crossinline f: (@DSL MediaControlBar).() -> Unit = {}) =
        MediaControlBar().also {
            tryAttach(it, f)
        }

