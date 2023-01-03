plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.loginAdmin"
}

dependencies {

    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.lifecycle.runtime)


    testImplementation (libs.junit)



    implementation (libs.androidx.hilt.navigation)

    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)


    implementation(project(":common"))
    api(project(":feature:loginAdmin:presentation"))
    implementation(project(":feature:login:data"))
    implementation(project(":feature:loginAdmin:domain"))
}