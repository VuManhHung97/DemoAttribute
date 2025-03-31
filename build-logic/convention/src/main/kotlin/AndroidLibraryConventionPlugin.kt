import com.android.build.api.dsl.LibraryExtension
import extentions.implementation
import extentions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import st.ottclouds.template.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
        apply("kotlin-parcelize")
        apply("ottclouds.android.library.compose")
        apply("ottclouds.android.library.flavors")
      }

      extensions.configure<LibraryExtension> {
        buildFeatures {
          viewBinding = true
          buildConfig = true
        }

        configureKotlinAndroid(this)

        dependencies {
          implementation(dependencies.coreResourceModule)
          implementation(libs.findLibrary("timber").get())
          implementation(libs.findLibrary("kotlin.result").get())
          implementation(libs.findLibrary("kotlin.result.coroutine").get())
        }
      }
    }
  }
}
