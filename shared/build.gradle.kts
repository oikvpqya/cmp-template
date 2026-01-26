import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.jetbrainsComposeFoundation)
                implementation(libs.jetbrainsComposeMaterial3)
                implementation(libs.jetbrainsComposeUiToolingPreview)
                implementation(libs.kotlinxCoroutinesCore)
                implementation(libs.kotlinxSerializationCore)
                implementation(libs.kotlinxSerializationJson)
                implementation(projects.runtime.ui)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.cashMolecule)
                implementation(libs.cashTurbine)
                implementation(libs.kotlinTest)
                implementation(libs.kotlinxCoroutinesTest)
            }
        }
    }
}

android {
    compileSdk = 36
    namespace = "me.oikvpqya.name.shared"

    defaultConfig {
        minSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
