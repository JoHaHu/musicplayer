package com.johupe.musicplayer


import javafx.application.Application
import javafx.stage.Stage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext



@Configurable
class MusicplayerApplication : Application() {

    private lateinit var context: ConfigurableApplicationContext
    private val logger = LoggerFactory.getLogger(MusicplayerApplication::class.java)

    override fun start(primaryStage: Stage) {
        context.beanFactory.registerSingleton("primaryStage", primaryStage)
        context.autowireCapableBeanFactory.autowireBean(this)
        primaryStage.show()
    }

    override fun init() {
        context = SpringApplication.run(AppConfig::class.java, *parameters.raw.toTypedArray())

    }

    override fun stop() {
        context.close()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            launch(MusicplayerApplication::class.java, *args)

        }
    }
}
