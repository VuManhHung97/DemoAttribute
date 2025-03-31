import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import st.ottclouds.template.configureKotlinAndroid

class AndroidCoreConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
        apply("kotlin-parcelize")
        apply("ottclouds.android.library.flavors")
      }

      extensions.configure<LibraryExtension> {
        buildFeatures {
          viewBinding = true
          buildConfig = true
        }

        configureKotlinAndroid(this)
      }
    }
  }
}
