val koin_version = "3.2.2"
val koin_android_version = "3.2.2"
val koin_android_compose_version = "3.2.1"
val koin_ktor = "3.2.2"
val moko_mvvm_version = "0.14.0"
val decompose_version = "1.0.0-alpha-06"
val destinations_version = "1.6.20-beta"

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

    id("io.realm.kotlin")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"

            export("dev.icerock.moko:mvvm-core:$moko_mvvm_version")
            export("dev.icerock.moko:mvvm-flow:$moko_mvvm_version")
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.realm.kotlin:library-base:1.2.0")

                api("dev.icerock.moko:mvvm-core:$moko_mvvm_version")
                api("dev.icerock.moko:mvvm-flow:$moko_mvvm_version")

                api("io.insert-koin:koin-core:$koin_version")
                api("io.insert-koin:koin-test:$koin_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("io.insert-koin:koin-android:$koin_version")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.notekmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}