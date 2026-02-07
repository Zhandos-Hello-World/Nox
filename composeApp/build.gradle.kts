import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    val iosTargets = listOf(iosArm64(), iosSimulatorArm64())

    iosTargets.forEach { target ->
        target.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // Если вы хотите вызывать методы TDLib напрямую из Swift кода:
//            api(project(":data"))
            // Линкеру всё равно нужно знать, где лежит tdlib
            linkerOpts("-F${project(":data").projectDir}/native_libs", "-framework", "TDLibFramework")
        }
    }
    
    jvm()
    
    sourceSets {
        androidMain.dependencies {
            implementation(project(":domain"))
            implementation(project(":data"))

            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("androidx.core:core-splashscreen:1.2.0")
            api(project(":tdlib_android"))

        }
        commonMain.dependencies {
            implementation(project(":domain"))
            implementation(project(":data"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.coroutinesCore)

            //compose lifecycle
            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.10.0") // или новее
            implementation("androidx.lifecycle:lifecycle-viewmodel:2.10.0")

            //coil
            implementation("com.github.skydoves:landscapist-coil3:2.8.3")

            //DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewModel)

            //NAVIGATION 3
            implementation(libs.jetbrains.navigation3.ui)

            //TIME CONVERTER
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")

            //CONSTRAINTS
//            implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.6.1")
//            implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.6.1-shaded-core")
//            implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.6.1-shaded")

            //ICONS
            implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

android {
    namespace = "io.bz.nox"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.bz.nox"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            multiDexEnabled = true
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "io.bz.nox.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.bz.nox"
            packageVersion = "1.0.0"
        }
    }
}
