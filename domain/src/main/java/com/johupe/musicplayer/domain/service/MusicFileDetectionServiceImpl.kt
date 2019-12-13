package com.johupe.musicplayer.domain.service

import com.johupe.musicplayer.core.events.StartFileWatcherServiceEvent
import com.johupe.musicplayer.domain.model.entities.MusicData
import javafx.collections.FXCollections
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.nio.file.*

@Service
class MusicFileDetectionServiceImpl(
        private val musicDataRepository: MusicDataRepository,
        private val watchService: WatchService,
        private val converter: MusicFileConverter
) : MusicFileDetectionService {
    private val musicData = FXCollections.observableArrayList<MusicData>()

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MusicFileDetectionServiceImpl::class.java)
    }


    fun removeMusicData() {

    }

    @Async
    fun scanLibraries() {


    }

    @Async
    fun handle(key: WatchKey, event: WatchEvent<*>) {

    }

    @Async
    @EventListener
    fun handleFileWatchServiceStartEvent(event: StartFileWatcherServiceEvent) {
        try {
            watchService.use {
                val keys = arrayListOf<WatchKey>()

                // TODO use settings to configure search paths
                val path = Paths.get(System.getProperty("user.home") + "/Music")
                path.toFile().walk(FileWalkDirection.TOP_DOWN)
                        .filter { it.isDirectory }
                        .map { it.toPath() }
                        .forEach {
                            keys += it.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE)
                        }
                while (true) {
                    val key = watchService.take()
                    for (wEvent in key.pollEvents()) {
                        handle(key, wEvent)
                    }
                    key.reset()
                }
            }
        } catch (e: InterruptedException) {
        } catch (e: ClosedWatchServiceException) {
        }
    }
}


