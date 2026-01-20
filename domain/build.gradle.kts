import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
//    jvm() // Desktop

    //Android target
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutinesCore)
            }
        }

//        val jvmMain by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.annotation)
            }
        }

        // Создаём общий sourceSet для iOS
        val iosMain by creating {
            dependsOn(commonMain)
        }

        // Привязываем платформенные sourceSets к iosMain
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}
