package com.johupe.jupiterfx

import javafx.application.Platform
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor


// TODO replace this Extension with a whole JavaFx Testengine
class JavaFxExtension : TestInstancePostProcessor {
    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {

        try {
            Platform.startup { }
        } catch (e: IllegalStateException) {

        }


    }


}


