import com.android.build.api.dsl.ApplicationExtension
import extentions.implementation
import extentions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import st.ottclouds.template.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.android")
        apply("kotlin-parcelize")
        apply("ottclouds.android.application.compose")
        apply("ottclouds.android.application.flavors")
      }

      extensions.configure<ApplicationExtension> {
        buildFeatures {
          viewBinding = true
          buildConfig = true
        }

        configureKotlinAndroid(this)

        dependencies {
          implementation(dependencies.coreResourceModule)
          implementation(libs.findLibrary("androidx.core.ktx").get())
          implementation(libs.findLibrary("androidx.appcompat").get())
          implementation(libs.findLibrary("material").get())
          implementation(libs.findLibrary("androidx.activity").get())
          implementation(libs.findLibrary("androidx.constraintlayout").get())
          implementation(libs.findLibrary("timber").get())
          implementation(libs.findLibrary("kotlin.result").get())
          implementation(libs.findLibrary("kotlin.result.coroutine").get())
        }
      }
    }
  }
}
