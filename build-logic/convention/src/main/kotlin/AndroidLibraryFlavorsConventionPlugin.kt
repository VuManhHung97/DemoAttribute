import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.SetProperty
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import st.ottclouds.template.buildVariableConfig
import st.ottclouds.template.configureFlavors

interface AndroidLibraryFlavorsConventionPluginExtension {
  val keys: SetProperty<String>
}

class AndroidLibraryFlavorsConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      extensions.configure<LibraryExtension> {
        configureFlavors()
      }

      val ext = extensions.create("androidLibraryFlavors", AndroidLibraryFlavorsConventionPluginExtension::class.java)

      project.extensions
        .getByType<LibraryAndroidComponentsExtension>()
        .finalizeDsl { _ ->
          ext.keys.getOrElse(emptySet())
            .takeIf { it.isNotEmpty() }
            ?.let { buildVariableConfig<LibraryExtension>(keys = it.toList()) }
        }
    }
  }
}
