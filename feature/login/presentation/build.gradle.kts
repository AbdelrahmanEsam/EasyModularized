plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
    id("easy.android.hilt")
}

android {
    namespace = "com.apptikar.login.presentation"
}

dependencies {



    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)

    implementation (libs.androidx.constraintlayout.compose)


    //sdp and ssp dependency
    implementation (libs.ssp)
    implementation (libs.sdp)

    // navigation dependency
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)



    // lifecycle
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    //lottie
    implementation (libs.lottie)
    //lottie
    implementation (libs.lottie.compose)





    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)


    implementation(project(":common"))
    implementation(project(":feature:login:domain"))
}