import extentions.implementation
import extentions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("ottclouds.android.library")
        apply("ottclouds.android.hilt")
        apply("ottclouds.android.library.flavors")
      }

      dependencies {
        implementation(dependencies.coreNavigatorModule)
        implementation(dependencies.coreCommonModule)
        implementation(dependencies.coreResourceModule)
        implementation(dependencies.coreUiModule)
        implementation(dependencies.coreModelModule)
        implementation(libs.findLibrary("androidx.core.ktx").get())
        implementation(libs.findLibrary("androidx.activity").get())
        implementation(libs.findLibrary("androidx.appcompat").get())
      }
    }
  }
}
