plugins {
id("easy.android.feature")
id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.scan"
}

dependencies {






    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)
    debugImplementation (libs.androidx.compose.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)

    // navigation dependency
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)



    implementation(project(":common"))
    api(project(":feature:scan:presentation"))
    api(project(":feature:scan:data"))
    api(project(":feature:scan:domain"))
}