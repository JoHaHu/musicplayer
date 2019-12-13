package com.johupe.jupiterfx

import org.junit.jupiter.api.extension.ExtendWith
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(JavaFxExtension::class)
@Inherited
@MustBeDocumented
annotation class JavaFxTest
