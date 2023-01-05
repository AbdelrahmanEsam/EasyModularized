plugins {
    id("easy.android.library")
    id("easy.android.hilt")
}

android {
    namespace = "com.apptikar.scan.data"
}

dependencies {



    testImplementation (libs.junit)



    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)

    implementation(project(":feature:scan:domain"))

}