

import com.android.build.gradle.LibraryExtension
import com.apptikar.buildlogic.easy.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)


            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")



//            implementation (libs.androidx.compose.ui)
//            implementation (libs.androidx.compose.material)
//            implementation (libs.androidx.compose.tooling)
//            implementation (libs.androidx.lifecycle.runtime)
//            implementation (libs.androidx.activity.compose)


            dependencies {
                add("implementation",libs.findLibrary("androidx.compose.ui").get())
                add("implementation",libs.findLibrary("androidx.compose.material").get())
                add("implementation",libs.findLibrary("androidx.compose.tooling").get())
                add("implementation",libs.findLibrary("androidx.lifecycle.runtime").get())
                add("implementation",libs.findLibrary("androidx.activity.compose").get())
            }
        }



    }

}