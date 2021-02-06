repositories {
    jcenter()
    google()
}

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    val appBaseModulePlugin = "base-android-module-plugin"
    plugins {
        register(appBaseModulePlugin) {
            id = appBaseModulePlugin
            implementationClass = "BaseAndroidConfigurationPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.1.2")
    implementation(kotlin("gradle-plugin", "1.4.30"))
}