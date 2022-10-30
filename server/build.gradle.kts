import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization")
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "com.yt8492.gakusaivote"
version = "1.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/ktor")
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("com.soywiz.korlibs.klock:klock:3.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.4")
    implementation("io.ktor:ktor-server-netty:2.1.2")
    implementation("io.ktor:ktor-serialization:2.1.2")
    implementation("io.ktor:ktor-server-core:2.1.2")
    implementation("io.ktor:ktor-server-call-logging:2.1.2")
    implementation("io.ktor:ktor-server-cors:2.1.2")
    implementation("io.ktor:ktor-server-content-negotiation:2.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
    implementation("io.ktor:ktor-auth:1.6.8")
    implementation("io.ktor:ktor-auth-jwt:1.6.8")
    implementation("com.google.cloud:google-cloud-datastore:2.12.3")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}
