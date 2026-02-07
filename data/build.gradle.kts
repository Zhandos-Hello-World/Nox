import com.android.builder.dexing.isProguardRule
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets

    androidLibrary {
        namespace = "io.bz.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        isProguardRule("proguard-rules.pro")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    val iosTargets = listOf(iosArm64(), iosSimulatorArm64())

    iosTargets.forEach { target ->
        target.compilations.getByName("main") {
            val tdlib by cinterops.creating {
                definitionFile.set(project.file("src/nativeInterop/cinterop/tdlib.def"))

                val xcFrameworkPath = project.file("native_libs/TDLibFramework.xcframework").absolutePath

                // 1. Определяем имя папки архитектуры внутри xcframework
                val archDir = when (target.name) {
                    "iosArm64" -> "ios-arm64"
                    "iosSimulatorArm64" -> "ios-arm64_x86_64-simulator"
                    else -> "ios-arm64"
                }

                // 2. Путь к конкретной папке, где лежит .framework
                // Именно эту папку мы передаем линкеру через -F
                val frameworkParentDir = "$xcFrameworkPath/$archDir"

                compilerOpts("-F$frameworkParentDir", "-framework", "TDLibFramework")
            }
        }

        target.binaries.all {
            val xcFrameworkPath = project.file("native_libs/TDLibFramework.xcframework").absolutePath
            val archDir = if (target.name.contains("Simulator")) "ios-arm64_x86_64-simulator" else "ios-arm64"
            linkerOpts("-F$xcFrameworkPath/$archDir", "-framework", "TDLibFramework")
            linkerOpts("-lc++")
        }
    }

    sourceSets {
        commonMain.dependencies {
            // общие зависимости
        }

        val iosMain by creating {
        }
    }


    jvm()

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {


        commonMain {
            dependencies {
                implementation(project(":domain"))

                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutinesCore)

                //DI
                implementation(libs.koin.core)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                api(project(":tdlib_android"))
                implementation(libs.androidx.annotation)
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }

}