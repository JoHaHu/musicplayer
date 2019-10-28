import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    kotlin("jvm")
    id("org.openjfx.javafxplugin")
}

javafx {
    version = "12.0.1"
    modules = arrayListOf("javafx.graphics")

}


tasks.withType<KotlinCompile> {

    kotlinOptions.jvmTarget = "12"
}

group = "johupe"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", "1.3.2")

    val junitPlatformVersion = "1.5.2"
    testImplementation(kotlin("test"))
    implementation("org.junit.jupiter", "junit-jupiter-api", "5.5.2")
    implementation("org.junit.platform", "junit-platform-commons", junitPlatformVersion)
    testRuntime("org.junit.platform", "junit-platform-launcher", junitPlatformVersion)
}

java {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}
