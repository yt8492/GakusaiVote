import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

group = "com.yt8492"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
            webpackTask {
                outputFileName = "web.js"
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(kotlin("stdlib-js"))
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("io.ktor:ktor-client-core:2.1.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
                implementation("com.soywiz.korlibs.klock:klock:3.3.0")
            }
        }
    }
}