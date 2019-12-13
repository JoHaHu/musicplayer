package com.johupe.jupiterfx

import javafx.application.Platform
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


@JavaFxTest
class JupiterFxTest {

    @Test
    @DisplayName("FX Toolkit is started")
    fun toolkitStarted() {

        assertThrows<IllegalStateException>( "FX Platform wasn't started and didn't throw illegalStateException"){
            Platform.startup{}
        }
    }
}
