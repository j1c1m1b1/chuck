package com.j1c1m1b1.chuck.app

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskCollection
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidAppConfigurationPlugin : Plugin<Project> {

    private val Project.androidExtension: BaseAppModuleExtension?
        get() = extensions.getByName(ANDROID_NAME_EXTENSION) as? BaseAppModuleExtension

    private val Project.kotlinTasks: TaskCollection<KotlinCompile>
        get() = tasks.withType(KotlinCompile::class.java)

    override fun apply(target: Project) {
        target.plugins.apply {
            apply(ANDROID_KOTLIN_PLUGIN_ID)
        }

        target.androidExtension?.configure(target.kotlinTasks)
    }

    private fun BaseAppModuleExtension.configure(kotlinTasks: TaskCollection<KotlinCompile>) {
        compileSdkVersion(COMPILE_SDK_VERSION)
        configureJava8Compatibility(kotlinTasks)
        setDefaultConfig()
        buildFeatures.viewBinding = true
    }

    private fun BaseAppModuleExtension.configureJava8Compatibility(kotlinTasks: TaskCollection<KotlinCompile>) {
        val javaVersion = JavaVersion.VERSION_1_8
        compileOptions {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        kotlinTasks.configureEach {
            kotlinOptions.jvmTarget = javaVersion.toString()
        }
    }

    private fun BaseAppModuleExtension.setDefaultConfig() {
        defaultConfig {
            minSdkVersion(MIN_SDK_VERSION)
            targetSdkVersion(COMPILE_SDK_VERSION)
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
            versionCode = VERSION
            versionName = VERSION_NAME
        }
    }

    private companion object {
        const val ANDROID_NAME_EXTENSION = "android"
        const val ANDROID_KOTLIN_PLUGIN_ID = "kotlin-android"
        const val COMPILE_SDK_VERSION = 30
        const val MIN_SDK_VERSION = 30
        const val VERSION = 1
        const val VERSION_NAME = "1.0"
        const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    }
}