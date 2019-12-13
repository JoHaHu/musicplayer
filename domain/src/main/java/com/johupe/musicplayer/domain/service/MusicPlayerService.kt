package com.johupe.musicplayer.domain.service

import com.johupe.musicplayer.domain.model.media.Playlist
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer


interface MusicPlayerService {
    fun stop()
    fun pause()
    fun next()
    fun prev()
    fun play(vararg medias: Media)
    fun addToPlaylist(vararg medias: Media)
    fun clearPlaylist()
    fun removeFromPlaylist(vararg medias: Media)
    fun playlistProperty(): ReadOnlyObjectProperty<Playlist>
    fun mediaPlayerProperty(): ReadOnlyObjectProperty<MediaPlayer?>
    val mediaPlayer: MediaPlayer?
}

interface MediaService

