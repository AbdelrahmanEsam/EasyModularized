plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.login"
}

dependencies {



    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)






    implementation(project(":common"))
    implementation(project(":feature:login:presentation"))
    api(project(":feature:login:domain"))
    api(project(":feature:login:data"))
}