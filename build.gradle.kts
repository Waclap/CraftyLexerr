plugins {
    kotlin("jvm") version "2.4.0"
    `maven-publish`
}

group = "com.github.Waclap"
version = "v1.1.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}