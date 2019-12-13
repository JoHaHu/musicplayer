package com.johupe.musicplayer.core.scopes

import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class OpensScope(val value: KClass<out BaseScope>)
