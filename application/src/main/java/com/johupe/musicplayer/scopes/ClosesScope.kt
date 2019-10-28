package com.johupe.musicplayer.scopes

import java.lang.annotation.*
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class ClosesScope(val value: KClass<out BaseScope>)
