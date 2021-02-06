const val kotlinVersion = "1.4.30"

object BuildPlugins {
    private object Versions {
        const val buildToolsVersion = "4.1.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val javaLibrary = "java-library"
    const val kotlin = "kotlin"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
}
