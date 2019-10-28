import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val developmentOnly by configurations.creating
val agent by configurations.creating

plugins {

    kotlin("jvm")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.jetbrains.kotlin.plugin.jpa")
    id("org.jetbrains.dokka")
    application
    id("io.spring.dependency-management")
    id("org.openjfx.javafxplugin")
    id("org.beryx.jlink")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
    agent.run {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}

group = "com.johupe.musicplayer"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}

val compileJava: JavaCompile by tasks

javafx {
    version = "12"
    modules = arrayListOf("javafx.controls")
}


configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
        extendsFrom(agent)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    agent.run {

    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")


    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", "1.3.2")

    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")

    developmentOnly("org.springframework.boot", "spring-boot-devtools")
    runtimeOnly("com.h2database", "h2")

    agent("org.springframework", "spring-instrument")

    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
            .exclude("org.junit.vintage", "junit-vintage-engine")
            .exclude("org.skyscreamer", "jsonassert")
    testImplementation(project(":jupiter-javafx"))
    testRuntime("org.junit.platform", "junit-platform-launcher")
}



kapt {
    arguments {
        arg("-Xmodule-path", compileJava.classpath.asPath)
    }

    javacOptions {
        option("--module-path", compileJava.classpath.asPath)
    }
}

tasks.withType(KotlinCompile::class.java) {
    kotlinOptions.jvmTarget = "12"

}


application {
    mainClassName = "$moduleName/com.johupe.musicplayer.MusicplayerApplication"
    applicationName = "Musicplayer"
    val args = arrayListOf("--add-modules", "javafx.controls")
    applicationDefaultJvmArgs = args
}

jlink {

    addOptions("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    addExtraDependencies("javafx")
    mergedModule {
        requires("java.instrument")
        requires("spring.boot.starter.data.jpa")

    }
}


tasks.named<CreateStartScripts>("startScripts") {
    val args = arrayListOf("--add-modules", "javafx.controls")
    args += agent.resolvedConfiguration.files.map { "-javaagent:../lib/${it.name}" }
    defaultJvmOpts = args
}

val run by tasks.run
run.run {

    jvmArgs!! += agent.resolvedConfiguration.files.map { "-javaagent:${it.absolutePath}" }

}



