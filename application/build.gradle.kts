

val run: JavaExec = tasks.getByName<JavaExec>("run")
val agent by configurations.creating

plugins {
    application
    kotlin("kapt")
    id("org.openjfx.javafxplugin")
    id("io.spring.dependency-management")
}


javafx {
    version = "12"
    modules("javafx.controls","javafx.graphics", "javafx.media")
}

application {
    mainClassName = "$moduleName/com.johupe.musicplayer.application.MusicplayerApplication"
    applicationName = "Musicplayer"
    val args = arrayListOf(
            "--add-opens", "java.base/java.lang=org.aspectj.weaver",
            "--add-opens", "javafx.base/com.sun.javafx.beans=spring.core"
    )
    applicationDefaultJvmArgs = args
}
dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
    agent.apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}
configurations {
    runtimeClasspath {
        extendsFrom(agent)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    runtimeOnly{
        extendsFrom(agent)
    }
}


dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":aspects"))
    implementation(project(":kotlinfx"))

    implementation("org.springframework.boot", "spring-boot-starter-quartz")

    agent("org.springframework", "spring-instrument")
    agent("org.aspectj", "aspectjweaver")

    kapt("org.springframework.boot", "spring-boot-configuration-processor")

    testApi("org.junit.jupiter", "junit-jupiter-api")
    testApi("org.springframework.boot", "spring-boot-starter-test")
            .exclude("org.junit.vintage", "junit-vintage-engine")
            .exclude("org.skyscreamer", "jsonassert")

    testRuntimeOnly("org.junit.platform", "junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform {
    }
    jvmArgs!!.plusAssign(agent.resolvedConfiguration.files.map { it.absolutePath })

}

run.run {
    jvmArgs!!.addAll(agent.resolvedConfiguration.files.map { "-javaagent:${it.absolutePath}" })
}

kapt {

    val compileJava: JavaCompile by tasks
    arguments {
        arg("-Xmodule-path", compileJava.classpath.asPath)
    }
    javacOptions {
        option("--module-path", compileJava.classpath.asPath)
    }
    includeCompileClasspath = false
}
