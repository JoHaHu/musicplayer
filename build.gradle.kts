import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.dokka") version "0.9.18"
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.openjfx.javafxplugin") version "0.0.8" apply false
    id("org.beryx.jlink") version "2.16.2" apply false
}

allprojects{

    repositories {
        mavenCentral()
    }
}

subprojects{


    group = "com.johupe.musicplayer"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "org.openjfx.javafxplugin")

    val developmentOnly by configurations.creating
    val compileJava: JavaCompile by tasks

    configurations {
        runtimeClasspath {
            extendsFrom(developmentOnly)
        }
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    the<org.jetbrains.kotlin.gradle.plugin.KaptExtension>().run {
        arguments {
            arg("-Xmodule-path", compileJava.classpath.asPath)
        }
        javacOptions {
            option("--module-path", compileJava.classpath.asPath)
        }
        includeCompileClasspath = false
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "12"
    }


}
