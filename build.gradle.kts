// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}
