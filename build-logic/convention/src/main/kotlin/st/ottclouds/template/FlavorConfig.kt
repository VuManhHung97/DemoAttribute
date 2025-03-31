package st.ottclouds.template

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension

@Suppress("EnumEntryName")
enum class OttCloudsBuildType(val applicationIdSuffix: String?, val versionNameSuffix: String?) {
  debug(
    applicationIdSuffix = ".dev",
    versionNameSuffix = "-dev",
  ),
  staging(
    applicationIdSuffix = ".stg",
    versionNameSuffix = "-stg",
  ),
  release(
    applicationIdSuffix = null,
    versionNameSuffix = null,
  ),
  ;

  companion object {
    fun getBaseSuffixes() = null to null
  }
}

@Suppress("EnumEntryName")
enum class FlavorDimension {
  environment,
}

@Suppress("EnumEntryName")
enum class OttCloudsFlavor(
  val applicationId: String,
  val dimension: FlavorDimension,
  val versionCode: Int,
  val versionName: String,
) {
  base(
    applicationId = "com.ottclouds.templateapp",
    dimension = FlavorDimension.environment,
    versionCode = 1,
    versionName = "1.0.0",
  ),
  ottclouds(
    applicationId = "com.st.ottclouds.cms",
    dimension = FlavorDimension.environment,
    versionCode = 1,
    versionName = "1.0.0",
  ),
}

@OptIn(ExperimentalStdlibApi::class)
internal fun CommonExtension<*, *, *, *, *, *>.configureFlavors() {
  flavorDimensions += FlavorDimension.entries.map { it.name }

  buildTypes {
    getByName(OttCloudsBuildType.debug.name) {
      isMinifyEnabled = false

      if (this@configureFlavors is ApplicationExtension && this is ApplicationBuildType) {
        isDebuggable = true
        applicationIdSuffix = OttCloudsBuildType.debug.applicationIdSuffix
        versionNameSuffix = OttCloudsBuildType.debug.versionNameSuffix
      }
    }

    create(OttCloudsBuildType.staging.name) {
      initWith(getByName(OttCloudsBuildType.debug.name))
      isMinifyEnabled = false

      if (this@configureFlavors is ApplicationExtension && this is ApplicationBuildType) {
        isDebuggable = true
        applicationIdSuffix = OttCloudsBuildType.staging.applicationIdSuffix
        versionNameSuffix = OttCloudsBuildType.staging.versionNameSuffix
      }
    }

    getByName(OttCloudsBuildType.release.name) {
      isMinifyEnabled = true
      // TODO(ConfigApp): Handle shrink resource and add proguard rules
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

      if (this@configureFlavors is ApplicationExtension && this is ApplicationBuildType) {
        isDebuggable = false
      }
    }
  }

  productFlavors {
    OttCloudsFlavor.entries.forEach { ottCloudsFlavor ->
      register(ottCloudsFlavor.name) {
        dimension = ottCloudsFlavor.dimension.name
        if (this@configureFlavors is ApplicationExtension && this is ApplicationProductFlavor) {
          applicationId = ottCloudsFlavor.applicationId
          versionCode = ottCloudsFlavor.versionCode
          versionName = ottCloudsFlavor.versionName
        }
      }
    }
  }

  productFlavors.all {
    if (this@configureFlavors is ApplicationExtension &&
      this is ApplicationProductFlavor &&
      name == OttCloudsFlavor.base.name
    ) {
      buildTypes.all {
        val (appIdSuffix, versionSuffix) = OttCloudsBuildType.getBaseSuffixes()
        applicationIdSuffix = appIdSuffix
        versionNameSuffix = versionSuffix
      }
    }
  }
}
