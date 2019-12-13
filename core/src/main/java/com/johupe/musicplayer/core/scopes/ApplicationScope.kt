package com.johupe.musicplayer.core.scopes

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ApplicationScope : BaseScope(LoggerFactory.getLogger(ApplicationScope::class.java))
