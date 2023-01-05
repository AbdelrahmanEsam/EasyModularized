plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
    id("easy.android.hilt")
}

android {
    namespace = "com.apptikar.scan.presentation"
}

dependencies {

    implementation (libs.androidx.constraintlayout.compose)

    //sdp and ssp dependency
    implementation (libs.ssp)
    implementation (libs.sdp)

    //lottie
    implementation (libs.lottie.compose)

    implementation (libs.retrofit.core)

    implementation(project(":common"))
    implementation(project(":feature:scan:domain"))
}