

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {

    /**
     * Apply this plugin to the given target object.
     *
     * @param target The target object
     */
    override fun apply(target: Project) {
        with(target)
        {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.kapt")
                apply("dagger.hilt.android.plugin")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation",libs.findLibrary("hilt.android").get())
                add("kapt",libs.findLibrary("hilt.compiler").get())
                add("kapt",libs.findLibrary("androidx.hilt.compiler").get())
                add("kaptAndroidTest",libs.findLibrary("hilt.compiler").get())
            }


        }
    }
}