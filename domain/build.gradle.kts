plugins {
    id("org.openjfx.javafxplugin")
}


javafx {
    version = "12"
    modules("javafx.media")
}


dependencies {
    implementation(project(":core"))
    implementation(project(":kotlinfx"))

    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")

    api("org.jetbrains.kotlinx", "kotlinx-coroutines-javafx", "1.3.2")


    developmentOnly("org.springframework.boot", "spring-boot-devtools")
    runtimeOnly("com.h2database", "h2")

    testImplementation(project(":jupiterfx"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
            .exclude("org.junit.vintage", "junit-vintage-engine")
            .exclude("org.skyscreamer", "jsonassert")
    testImplementation("org.reflections", "reflections", "0.9.11")
    testImplementation("io.github.classgraph", "classgraph", "4.8.53")
    testRuntimeOnly("org.junit.platform", "junit-platform-launcher")
}


