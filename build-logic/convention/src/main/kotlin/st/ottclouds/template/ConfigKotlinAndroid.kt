package st.ottclouds.template

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.CommonExtension
import extentions.coreLibraryDesugaring
import extentions.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
  configSdk(commonExtension)

  commonExtension.apply {
    compileOptions {
      isCoreLibraryDesugaringEnabled = true

      // Up to Java 11 APIs are available through desugaring
      // https://developer.android.com/studio/write/java11-minimal-support-table
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
    }
  }

  dependencies {
    coreLibraryDesugaring(libs.findLibrary("desugar.jdk.libs").get())
  }

  configureKotlin()
}

internal fun configSdk(commonExtension: CommonExtension<*, *, *, *, *, *>) {
  commonExtension.apply {
    compileSdk = 35

    defaultConfig {
      minSdk = 26

      if (this is ApplicationDefaultConfig) {
        targetSdk = 35
      }
    }
  }
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() = configure<KotlinAndroidProjectExtension> {
  // Treat all Kotlin warnings as errors (disabled by default)
  // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
  val warningsAsErrors: String? by project
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_11)
    allWarningsAsErrors.set(warningsAsErrors.toBoolean())
    freeCompilerArgs.addAll(
      // Enable experimental coroutines APIs, including Flow
      "-opt-in=kotlin.RequiresOptIn",
      "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
      "-opt-in=kotlinx.coroutines.FlowPreview",
    )

    if (project.findProperty("composeCompilerReports") == "true") {
      freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.layout.buildDirectory}/compose_compiler",
      )
    }

    if (project.findProperty("composeCompilerMetrics") == "true") {
      freeCompilerArgs.addAll(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.layout.buildDirectory}/compose_compiler",
      )
    }
  }
}
