plugins {
    id("org.openjfx.javafxplugin")
}


javafx {
    version = "12"
    modules("javafx.graphics")
}

dependencies {
    api("org.springframework.boot", "spring-boot-starter")
    api(kotlin("stdlib"))
    api(kotlin("reflect"))


    testApi("org.junit.jupiter", "junit-jupiter-api")
    testApi("org.springframework.boot", "spring-boot-starter-test")
            .exclude("org.junit.vintage", "junit-vintage-engine")
            .exclude("org.skyscreamer", "jsonassert")

    testRuntimeOnly("org.junit.platform", "junit-platform-launcher")
}


