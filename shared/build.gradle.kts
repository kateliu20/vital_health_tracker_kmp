import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.compose)
  alias(libs.plugins.kotlin.plugin.compose)
  alias(libs.plugins.spotless)
  alias(libs.plugins.sqldelight)
}

spotless {
  kotlin {
    target("src/**/*.kt")
    ktfmt().googleStyle()
    trimTrailingWhitespace()
    endWithNewline()
  }
  kotlinGradle {
    target("*.gradle.kts")
    ktfmt().googleStyle()
    trimTrailingWhitespace()
    endWithNewline()
  }
}

kotlin {
  androidTarget {
    compilations.all {
      compileTaskProvider.configure { compilerOptions { jvmTarget.set(JvmTarget.JVM_1_8) } }
    }
  }

  listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach {
    it.binaries.framework {
      baseName = "shared"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.ui)
      implementation(compose.components.resources)

      api(libs.compose.webview.multiplatform)

      implementation(libs.kotlinx.datetime)
      implementation(libs.koalaplot.core)
      implementation(libs.kmp.date.time.picker)
      implementation(libs.kmp.uiud)

      implementation(libs.sqldelight.coroutines)
      implementation(libs.sqldelight.async)
    }
    commonTest.dependencies { implementation(libs.kotlin.test) }

    androidMain.dependencies { implementation(libs.sqldelight.driver.android) }

    iosMain.dependencies { implementation(libs.sqldelight.driver.native) }
  }
}

android {
  namespace = "com.example.healthtracker"
  compileSdk = 35
  defaultConfig { minSdk = 24 }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(libs.androidx.runtime.android)
  implementation(libs.compose.material3)
  implementation(libs.androidx.core.i18n)
}

sqldelight {
  databases {
    create("BloodPressure") {
      packageName.set("com.example.healthtracker.sqldelight")
      generateAsync.set(true)
    }
  }
}
