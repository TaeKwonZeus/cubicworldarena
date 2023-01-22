plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    java
}

group = "net.cubicworld"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("com.mysql:mysql-connector-j:8.0.32")

    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

artifacts.archives(tasks.shadowJar)

tasks {
    jar {enabled = false}

    shadowJar {
        archiveFileName.set(rootProject.name + ".jar")
        val dependencyPackage = "${rootProject.group}.dependencies.${rootProject.name.toLowerCase()}"
        relocate("com.mysql", "${dependencyPackage}.mysql")
    }
}
