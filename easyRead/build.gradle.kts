plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId =  "com.apptikar.easy"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


    buildFeatures {
        compose =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion  = "1.3.0-rc01"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation("androidx.compose.material:material:1.3.1")

    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso)
    androidTestImplementation (libs.androidx.compose.ui.test)

    //sdp and ssp dependency
    implementation (libs.ssp)
    implementation (libs.sdp)

    // navigation dependency
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)



    // lifecycle
    implementation (libs.androidx.lifecycle.common)
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

      //lottie
    implementation (libs.lottie)

    // windowManger
    implementation(libs.window.manager)

    // hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation)


    //retrofit
    implementation (libs.retrofit.core)
    implementation (libs.retrofit.gson.converter)
    implementation (libs.retrofit.intercepter)

    //dataStore
    implementation (libs.androidx.datastore.pref)
    implementation (libs.androidx.datastore.core)



    implementation(project(":common"))
    implementation(project(":feature:login"))
    implementation(project(":feature:scan"))
}