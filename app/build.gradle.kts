import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").reader())
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.simform.app"
    compileSdk = libs.versions.compile.sdk.version.get().toInt()

    signingConfigs {
        getByName("debug") {
           storeFile = file("${rootDir}/keystore/debug.keystore")
           storePassword = "android"
           keyAlias = "androiddebugkey"
           keyPassword = "android"
        }
        maybeCreate("release").apply {
            storeFile = file(localProperties["storeFile"].toString())
            storePassword = localProperties["storePass"].toString()
            keyAlias = localProperties["keyAlias"].toString()
            keyPassword = localProperties["keyPass"].toString()
        }
    }

    defaultConfig {
        applicationId = "com.simform.app"
        minSdk = libs.versions.min.sdk.version.get().toInt()
        targetSdk = libs.versions.compile.sdk.version.get().toInt()
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    flavorDimensions += "default"

    productFlavors {
        create("dev") {
            manifestPlaceholders += secretManifestPlaceholders(name)
        }

        create("qa") {
            manifestPlaceholders += secretManifestPlaceholders(name)
        }

        create("production") {
            manifestPlaceholders += secretManifestPlaceholders(name)
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":ArkanaKeys"))
    implementation(project(":design"))
    implementation(project(":navigation"))

    // Core
    implementation(libs.androidx.core.ktx)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Startup
    implementation(libs.androidx.startup.runtime)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.material.navigation)
    implementation("androidx.compose.foundation:foundation-layout:1.7.3")

    // Material 3
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)

    // Navigation
    implementation(libs.navigation.compose)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit.kotlinx.serialization.converter)

    // Timber
    implementation(libs.timber)

    // Bagel
    implementation(libs.bagel)

    // Room DB
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}