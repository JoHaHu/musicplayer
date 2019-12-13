package com.johupe.musicplayer.application


import com.johupe.musicplayer.core.events.FxApplicationReadyEvent
import com.johupe.musicplayer.core.events.StartFileWatcherServiceEvent
import javafx.application.Application
import javafx.application.HostServices
import javafx.application.Platform
import javafx.stage.Stage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.GenericApplicationContext
import java.util.function.Supplier


class MusicplayerApplication : Application() {

    private lateinit var context: ConfigurableApplicationContext

    @Value(value = "\${application.ui.implicitExit:true}")
    private val implicitExit = true

    override fun start(primaryStage: Stage) {
        Platform.setImplicitExit(implicitExit)
        context.beanFactory.registerSingleton("primaryStage", primaryStage)
        context.publishEvent(StartFileWatcherServiceEvent(this))
        context.publishEvent(FxApplicationReadyEvent(this))

    }

    override fun init() {


        val initializer = ApplicationContextInitializer<GenericApplicationContext> {
            it.registerBean(MusicplayerApplication::class.java, Supplier<MusicplayerApplication> { this })
            it.registerBean(Parameters::class.java, Supplier<Parameters> { parameters })
            it.registerBean(HostServices::class.java, Supplier<HostServices> { hostServices })
        }
        context = SpringApplicationBuilder()
                .sources(AppConfig::class.java)
                .initializers(initializer)
                .run(*parameters.raw.toTypedArray())
        // TODO socket if already started
    }

    override fun stop() {
        context.close()
        Platform.exit()
    }

    companion object {
        @JvmStatic
        private val logger = LoggerFactory.getLogger(MusicplayerApplication::class.java)

        @JvmStatic
        fun main(args: Array<String>) {

            launch(MusicplayerApplication::class.java, *args)
        }
    }


}
