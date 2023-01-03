plugins {
    id("easy.android.library")

}

android {
    namespace = "com.apptikar.login.admin.domain"

}

dependencies {

    implementation(libs.androidx.core.kotlin)

    testImplementation (libs.junit)

    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)

//    implementation(project(":feature:login:data"))
}