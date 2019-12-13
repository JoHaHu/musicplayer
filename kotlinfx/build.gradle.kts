plugins {
    kotlin("jvm")
    id("org.openjfx.javafxplugin")
}

javafx {
    version = "12"
    modules = arrayListOf("javafx.controls", "javafx.media")
}

dependencies {

    api("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", "1.3.2")

    val junitPlatformVersion = "1.5.2"
    val junitVersion = "5.5.2"
    testImplementation(project(":jupiterfx"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter", junitVersion)
    testRuntimeOnly("org.junit.platform", "junit-platform-launcher", junitPlatformVersion)
}
