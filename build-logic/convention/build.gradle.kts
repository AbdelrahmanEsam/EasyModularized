plugins {
    `kotlin-dsl`
}

group = "com.apptikar.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt")
        {
            id = "easy.android.hilt"
           implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLibrary")
        {
            id = "easy.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidComposeFeature")
        {
            id = "easy.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }


        register("androidFeature")
        {
            id = "easy.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }



        register("androidApplication")
        {
            id = "easy.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose")
        {
            id = "easy.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"

        }
    }
}
