plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "com.yt8492.gakusaivote"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("com.soywiz.korlibs.klock:klock:3.3.0")
            }
        }
    }
}
