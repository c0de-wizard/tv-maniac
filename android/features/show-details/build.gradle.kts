@file:Suppress("UnstableApiUsage")

import util.libs

plugins {
    `android-feature-plugin`
}

android {
    namespace = "com.thomaskioko.showdetails"
}

dependencies {
    api(project(":shared:core:ui"))
    api(project(":shared:core:util"))
    api(projects.shared.domain.showDetails.api)
    api(project(":shared:domain:similar:api"))
    api(project(":shared:domain:genre:api"))
    api(projects.shared.domain.seasons.api)
    api(project(":shared:domain:show-common:api"))
    api(project(":shared:domain:last-air-episodes:api"))
    implementation(project(":android:common:compose"))

    implementation(libs.snapper)
    implementation(libs.accompanist.insetsui)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.material.icons)
}
