@file:Suppress("UnstableApiUsage")

import util.libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = libs.versions.android.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.min.get().toInt()
        targetSdk = libs.versions.android.target.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    sourceSets {
        val androidTest by getting
        val test by getting
        androidTest.java.srcDirs("src/androidTest/kotlin")
        test.java.srcDirs("src/test/kotlin")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get().toString()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":app-common:core"))
    implementation(project(":app-common:compose"))
    implementation(project(":app-common:navigation"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler)

    implementation(libs.napier)

    testImplementation(libs.testing.turbine)
    testImplementation(libs.testing.coroutines.test)
    testImplementation(libs.testing.kotest.assertions)

    testImplementation(libs.testing.mockito.inline)
    testImplementation(libs.testing.mockk.core)

    testImplementation(libs.testing.androidx.core)

    testImplementation(libs.testing.junit5.api)
    testRuntimeOnly(libs.testing.junit5.jupiter)
    testRuntimeOnly(libs.testing.junit5.engine)
    testRuntimeOnly(libs.testing.junit5.vintage)
}