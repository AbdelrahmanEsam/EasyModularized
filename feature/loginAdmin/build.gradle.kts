plugins {
    id("easy.android.feature")
    id("easy.android.library.compose")
}

android {
    namespace = "com.apptikar.loginAdmin"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:loginAdmin:presentation"))
    api(project(":feature:loginAdmin:data"))
    api(project(":feature:loginAdmin:domain"))
}