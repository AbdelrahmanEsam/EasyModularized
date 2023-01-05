plugins {
    id("easy.android.library")
    id("easy.android.hilt")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.login.admin.presentation"
}

dependencies {

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)


    implementation (libs.retrofit.core)


    implementation(project(":common"))
    implementation(project(":feature:loginAdmin:domain"))
}