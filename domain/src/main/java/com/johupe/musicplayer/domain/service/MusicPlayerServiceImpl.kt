package com.johupe.musicplayer.domain.service


import com.johupe.kotlinfx.getValue
import com.johupe.musicplayer.domain.model.media.Playlist
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class MusicPlayerServiceImpl(mediaIterator: Playlist) : MusicPlayerService {
    private val _playlistProperty: ObjectProperty<Playlist> = SimpleObjectProperty(mediaIterator)


    private val _mediaPlayerProperty: ObjectProperty<MediaPlayer?> = SimpleObjectProperty()
    private var playlistIterator = _playlistProperty.get().listIterator()

    override fun mediaPlayerProperty() = _mediaPlayerProperty
    override fun playlistProperty() = _playlistProperty
    override val mediaPlayer: MediaPlayer? by _mediaPlayerProperty

    private val endOfMediaListener = Runnable { next() }

    override fun clearPlaylist() {
        _playlistProperty.get().clear()
        playlistIterator = _playlistProperty.get().listIterator()
    }

    override fun play(vararg medias: Media) {

        if (medias.isEmpty()) {
            val player = mediaPlayerProperty().get()
            player?.let {
                it.play()
                it.onEndOfMedia = endOfMediaListener
            }
            // TODO implement last played service
            // TODO Implement playlist service
            return
        }
        _playlistProperty.get().clear()
        addToPlaylist(*medias)
        val media = _playlistProperty.get().poll()
        if (media != null) {
            val mediaPlayer = MediaPlayer(media)
            _mediaPlayerProperty.set(mediaPlayer)
            logger.info("Start playing ${media.source}")
            mediaPlayer.play()
            mediaPlayer.onEndOfMedia = endOfMediaListener
        } else {
            logger.info("No media in queue")
        }
    }

    override fun addToPlaylist(vararg medias: Media) {
        _playlistProperty.get().addAll(medias)
    }

    override fun removeFromPlaylist(vararg medias: Media) {
        _playlistProperty.get().removeAll(medias)
    }

    override fun prev() {

    }

    override fun pause() {
        mediaPlayer?.pause()
    }

    override fun stop() {
        mediaPlayer?.stop()
    }

    override fun next() {
        if (playlistIterator.hasNext()) {
            var media = playlistIterator.next()
            if (media == mediaPlayer?.media && playlistIterator.hasNext()) media = playlistIterator.next()
            val newMediaPlayer = MediaPlayer(media)
            _mediaPlayerProperty.set(newMediaPlayer)
        }
        mediaPlayer?.dispose()
        // TODO implement end of playlist
        // TODO Add a replay property
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MusicPlayerServiceImpl::class.java)
    }

}


