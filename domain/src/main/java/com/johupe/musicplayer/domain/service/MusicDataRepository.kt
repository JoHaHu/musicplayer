package com.johupe.musicplayer.domain.service

import com.johupe.musicplayer.domain.model.entities.MusicData
import org.springframework.data.jpa.repository.JpaRepository

interface MusicDataRepository : JpaRepository<MusicData, Long>

