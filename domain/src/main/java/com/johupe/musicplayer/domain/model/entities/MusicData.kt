package com.johupe.musicplayer.domain.model.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "musicdata")
@Table(name = "musicdata")
data class MusicData(
        var path: String,
        var title: String,
        var artist: String = "Unknown Artist",
        var album: String = "Unknown Album",
        var rating: Int = -1,
        @Id val id: Long = 0
)
