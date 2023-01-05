plugins {
    id ("easy.android.application")
    id("easy.android.application.compose")
    id("easy.android.hilt")
}

android {

    defaultConfig {
        applicationId =  "com.apptikar.easy"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
    }

//    ProductFlavors
//    {
//
//    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }



    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    implementation fileTree(include: ['*.jar'], dir: 'libs')
//    implementation files('libs/sunmiscan.jar')


    implementation (libs.androidx.constraintlayout)
    implementation(libs.androidx.core.kotlin)
    implementation (libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.compose.material)

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)

    //sdp and ssp dependency
    implementation (libs.ssp)
    implementation (libs.sdp)



    // lifecycle
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

      //lottie
    implementation (libs.lottie)




    //dataStore
    implementation (libs.androidx.datastore.pref)
    implementation (libs.androidx.datastore.core)



    implementation(project(":common"))
    implementation(project(":feature:login"))
    implementation(project(":feature:scan"))
}