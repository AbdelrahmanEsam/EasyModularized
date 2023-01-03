plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.common"

}

dependencies {

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.material)

    testImplementation (libs.junit)

    //dataStore
    implementation (libs.androidx.datastore.pref)
    implementation (libs.androidx.datastore.core)

    //windowManager
    implementation(libs.window.manager)

    // navigationCompose
    implementation(libs.androidx.navigation.compose)
}