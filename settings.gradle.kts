pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    includeBuild("build-logic")
    versionCatalogs {
        create("myLibs") {
            from(files("/gradle/libs.versions.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

}


rootProject.name = "Easy"

//App Modules
include (":easyWrite")
include (":easyRead")


include(":common")



//Feature Modules


//WriteOnTag
include(":feature:writeOnTag")



//Login
include(":feature:login")
include(":feature:login:data")

//LoginAdmin
include(":feature:loginAdmin")
include(":feature:loginAdmin:presentation")
include(":feature:loginAdmin:data")
include(":feature:loginAdmin:domain")



include(":feature:scan")
include(":feature:scan:presentation")
include(":feature:scan:domain")
include(":feature:scan:data")





