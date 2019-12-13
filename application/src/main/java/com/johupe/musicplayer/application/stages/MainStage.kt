package com.johupe.musicplayer.application.stages


import com.johupe.kotlinfx.*
import com.johupe.kotlinfx.views.mediacontrolbar
import com.johupe.musicplayer.core.scopes.ApplicationScope
import com.johupe.musicplayer.core.scopes.ClosesScope
import com.johupe.musicplayer.core.scopes.OpensScope
import com.johupe.musicplayer.domain.model.media.MusicTrack
import javafx.event.Event
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Scope("prototype")
@Component
class MainStage(
        @Qualifier("primaryStage") primaryStage: Stage,
        private val viewModel: MainStageViewModel
) : BaseStage(primaryStage) {

    override val root = borderpane {
        prefHeight = 400.0
        prefWidth = 600.0
        top = menubar {
            menu("_File") {
                item("_Quit") {
                    accelerator = KeyCombination.keyCombination("CTRL+Q")
                    setOnAction { this@MainStage.hide() }
                }
            }
            menu("_Help")
        }
        center = tableview<MusicTrack> {
            items = listOf(MusicTrack("Test")).observable()
            column("Title", MusicTrack::titleProperty)
            column("Length", MusicTrack::lengthProperty)
        }
        bottom = mediacontrolbar {

        }
    }

    @OpensScope(ApplicationScope::class)
    override fun show() {
        super.show()
        stage.isMaximized = true
    }

    @ClosesScope(ApplicationScope::class)
    override fun close(e: WindowEvent) {
        super.close(e)
    }

    fun hide() {
        Event.fireEvent(this, WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST))
    }

}
