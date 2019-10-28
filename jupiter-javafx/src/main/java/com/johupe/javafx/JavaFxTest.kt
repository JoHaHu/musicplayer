package com.johupe.javafx

import org.junit.jupiter.api.extension.ExtendWith

import java.lang.annotation.*

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(JavaFxExtension::class)
@Inherited
@MustBeDocumented
internal annotation class JavaFxTest
