import extentions.implementation
import extentions.ksp
import extentions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.run {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
      }
      dependencies {
        ksp(libs.findLibrary("hilt.compiler").get())
        implementation(libs.findLibrary("hilt.android").get())
      }
    }
  }
}
