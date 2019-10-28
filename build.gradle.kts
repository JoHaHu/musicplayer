plugins {
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("kapt") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion apply false
    id("org.jetbrains.dokka") version "0.9.18" apply false
    id("io.freefair.aspectj") version "4.1.2" apply false
    id("io.freefair.aspectj.post-compile-weaving") version "4.1.3" apply false
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    id("org.openjfx.javafxplugin") version "0.0.8" apply false
    id("org.beryx.jlink") version "2.16.2" apply false
}


