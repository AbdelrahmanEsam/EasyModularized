plugins {
    id("easy.android.library")
    id("easy.android.hilt")
}

android {
    namespace = "com.apptikar.login.admin.data"
}

dependencies {

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.activity.compose)
    testImplementation (libs.junit)


    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)

    implementation(project(":feature:loginAdmin:domain"))
}