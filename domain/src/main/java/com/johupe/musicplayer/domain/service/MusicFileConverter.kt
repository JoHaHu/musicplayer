package com.johupe.musicplayer.domain.service

import com.johupe.musicplayer.domain.model.entities.MusicData
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class MusicFileConverter {


    fun getArtist(path: Path): String {
        return ""
    }

    fun getTitle(path: Path): String {
        return ""
    }

    fun getAlbum(path: Path): String {
        return ""
    }

    fun getMusicData(path: Path): MusicData {

        return MusicData(path.toString(), getTitle(path), getArtist(path), getAlbum(path))
    }


}
