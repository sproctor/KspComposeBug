plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp")
}

group "com.example"
version "1.0-SNAPSHOT"

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
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation("me.tatarka.inject:kotlin-inject-runtime:0.6.1")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

dependencies {
    add("kspJs", "me.tatarka.inject:kotlin-inject-compiler-ksp:0.6.1")
}