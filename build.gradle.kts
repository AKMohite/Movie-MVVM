// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val compose_version = "1.7.6"
    val kotlin_version = "2.0.21"
    val dagger_hilt = "2.55"
    val room_version = "2.6.1"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin.plugin.compose:org.jetbrains.kotlin.plugin.compose.gradle.plugin:$kotlin_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$dagger_hilt")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.21-1.0.27")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
