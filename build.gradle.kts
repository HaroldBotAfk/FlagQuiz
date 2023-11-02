buildscript {
    dependencies {
        classpath("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:2.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "7.4.2" apply false
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}