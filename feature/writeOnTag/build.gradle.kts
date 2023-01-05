plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.writeontag"
}

dependencies {

    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)


    // lottieAnimation
    implementation (libs.lottie.compose)

    implementation(project(":common"))
}