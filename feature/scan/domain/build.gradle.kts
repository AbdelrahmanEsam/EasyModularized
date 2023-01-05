plugins {
    id("easy.android.library")
    id("easy.android.hilt")
}

android {
    namespace = "com.apptikar.scan.domain"

}

dependencies {



    testImplementation (libs.junit)

    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)
}