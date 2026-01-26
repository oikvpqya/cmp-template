import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.jetbrainsComposeRuntime)
                implementation(libs.kotlinxCoroutinesCore)
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
