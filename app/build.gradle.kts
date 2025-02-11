plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.mak.telflix"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.mak.telflix"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "TMDBAPIKey", "\"" + propOrDef("tmdb_api_key", "") + "\"")
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("release/app-debug.jks")
            storePassword = "otaku-android"
            keyAlias = "androidDebug"
            keyPassword = "otaku-android"
        }

        create("release") {
            if (rootProject.file("release/app-release.jks").exists()) {
                storeFile = rootProject.file("release/app-release.jks")
                storePassword = properties["TEL_FLIX_STORE_PWD"]?.toString() ?: ""
                keyAlias = "tel-flix"
                keyPassword = properties["TEL_FLIX_KEY_PWD"]?.toString() ?: ""
            }
        }
    }
    buildTypes {

        getByName("debug") {
            signingConfig = signingConfigs["debug"]
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".debug"
            buildConfigField("String", "TMDBAPIKey", "\"" + propOrDef("tmdb_api_key", "") + "\"")
//            resValue("string", "tmdb_api_key", "\"" + properties["tmdb_api_key"]?.toString() + "\"")
            resValue("string", "app_version", "${defaultConfig.versionName}${versionNameSuffix}")
        }

        getByName("release") {
            signingConfig = signingConfigs["release"] ?: signingConfigs["debug"]
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "TMDBAPIKey", "\"" + propOrDef("tmdb_api_key", "") + "\"")
//            resValue("string", "tmdb_api_key", "\"" + properties["tmdb_api_key"]?.toString() + "\"")
            resValue("string", "app_version", "${defaultConfig.versionName}")
        }
    }

    buildFeatures {
        buildConfig = true
    }
    flavorDimensions += listOf("mode")
    productFlavors {
        create("dev") {
            dimension = "mode"
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".dev"
        }
        create("prod") {
            dimension = "mode"
        }
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    implementation(fileTree(dir: "libs", include: ["*.jar"]))
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.compose.ui:ui:1.7.6")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha07")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    implementation("com.google.dagger:hilt-android:2.55")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-compiler:2.55")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.moshi:moshi:1.15.2")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")


    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.6")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.6")
}

fun <T : Any> propOrDef(propertyName: String, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    val propertyValue = project.properties[propertyName] as T?
    return propertyValue ?: defaultValue
}
