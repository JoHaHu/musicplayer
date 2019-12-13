import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openjfx.javafxplugin")
}

javafx {
    version = "12"
    modules = arrayListOf("javafx.controls")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "12"
}

tasks.test {
    useJUnitPlatform{
        includeEngines("junit-jupiter", "jupiterfx")
    }
}

dependencies {
    api(kotlin("stdlib"))
    api(kotlin("reflect"))
    api("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", "1.3.2")

    val junitPlatformVersion = "1.5.2"
    val junitVersion = "5.5.2"
    testImplementation(kotlin("test"))
    api("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter", junitVersion)
    testRuntimeOnly("org.junit.platform", "junit-platform-launcher", junitPlatformVersion)
}

