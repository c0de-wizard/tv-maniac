plugins {
    `kmm-domain-plugin`
}

dependencies {

    commonMainImplementation(project(":shared:core"))
    commonMainImplementation(project(":shared:database"))
    commonMainImplementation(project(":shared:remote"))
    commonMainImplementation(project(":shared:domain:show:api"))
    commonMainImplementation(project(":shared:domain:episodes:api"))
    commonMainImplementation(project(":shared:domain:last-air-episodes:api"))

    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kermit)
    commonMainImplementation(libs.kotlin.datetime)
    commonMainImplementation(libs.kotlin.coroutines.core)
    commonMainImplementation(libs.multiplatform.paging.core)
    commonMainImplementation(libs.squareup.sqldelight.extensions)

    testImplementation(libs.testing.mockk.core)

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(project(":shared:core-test"))
    commonTestImplementation(libs.testing.turbine)
    commonTestImplementation(libs.testing.kotest.assertions)
    commonTestImplementation(libs.testing.coroutines.test)
    commonTestImplementation(libs.testing.mockk.common)
}