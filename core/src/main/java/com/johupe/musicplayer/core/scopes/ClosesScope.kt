package com.johupe.musicplayer.core.scopes

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class ClosesScope(val value: KClass<out BaseScope>)
