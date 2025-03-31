import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import st.ottclouds.template.buildVariableConfig
import st.ottclouds.template.configureFlavors

class AndroidApplicationFlavorsConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      extensions.configure<ApplicationExtension> {
        configureFlavors()
      }
      buildVariableConfig<ApplicationExtension>(
        keys = listOf(
          "GOOGLE_OAUTH_CLIENT_ID",
        ),
      )
    }
  }
}
