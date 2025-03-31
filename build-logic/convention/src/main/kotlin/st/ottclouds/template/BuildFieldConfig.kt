package st.ottclouds.template

import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import java.util.Properties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import st.ottclouds.template.utils.readPropertiesFile

internal inline fun <reified T : CommonExtension<*, *, *, *, *, *>> Project.buildVariableConfig(keys: List<String>) =
  extensions.configure<T> {
    productFlavors.all flavor@{
      if (this@flavor.name == OttCloudsFlavor.base.name) {
        buildTypes.all {
          addBuildConfigStringFieldFromProperties(
            properties = readPropertiesFile("base.properties"),
            keys = keys,
          )
        }
        return@flavor
      }

      if (this@flavor.name == OttCloudsFlavor.ottclouds.name) {
        buildTypes.all {
          when (name) {
            OttCloudsBuildType.debug.name -> {
              addBuildConfigStringFieldFromProperties(
                properties = readPropertiesFile("ottclouds.dev.properties"),
                keys = keys,
              )
            }

            OttCloudsBuildType.staging.name -> {
              addBuildConfigStringFieldFromProperties(
                properties = readPropertiesFile("ottclouds.stg.properties"),
                keys = keys,
              )
            }

            OttCloudsBuildType.release.name -> {
              addBuildConfigStringFieldFromProperties(
                properties = readPropertiesFile("ottclouds.release.properties"),
                keys = keys,
              )
            }
          }
        }
      }
    }
  }

@Suppress("NOTHING_TO_INLINE")
internal inline fun BuildType.addBuildConfigStringFieldFromProperties(properties: Properties, keys: List<String>) {
  keys.forEach { addBuildConfigStringFieldFromProperties(properties, it) }
}

internal fun BuildType.addBuildConfigStringFieldFromProperties(properties: Properties, key: String) {
  properties.getProperty(key)?.let { value ->
    buildConfigField("String", key, "\"$value\"")
  } ?: println("Property '$key' not found in provided properties.")
}
