plugins {
    id("easy.android.library")
    id("easy.android.hilt")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.login.admin.presentation"
}

dependencies {

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.material)
    implementation (libs.androidx.compose.tooling)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.activity.compose)

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)



    implementation (libs.androidx.hilt.navigation)

    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)

    implementation(project(":common"))
    implementation(project(":feature:loginAdmin:domain"))
}