package com.johupe.musicplayer.domain.model.media

import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class MusicTrack(title: String, length: Int = 0) {

    val titleProperty: StringProperty = SimpleStringProperty(title)
    val lengthProperty: IntegerProperty = SimpleIntegerProperty(length)
}
