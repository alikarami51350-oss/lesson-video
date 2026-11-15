plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// Values come from GitHub Actions (variable SERVER_BASE_URL, secret APP_TOKEN).
fun esc(s: String) = s.replace("\\", "\\\\").replace("\"", "\\\"")
val serverBaseUrl = (System.getenv("SERVER_BASE_URL") ?: "").trim().trimEnd('/')
val appToken = (System.getenv("APP_TOKEN") ?: "").trim()

android {
    namespace = "ir.lessonvideo.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "ir.lessonvideo.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "SERVER_BASE_URL", "\"${esc(serverBaseUrl)}\"")
        buildConfigField("String", "APP_TOKEN", "\"${esc(appToken)}\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation("androidx.exifinterface:exifinterface:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
}
