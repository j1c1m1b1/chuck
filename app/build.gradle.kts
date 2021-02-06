import com.j1c1m1b1.chuck.AndroidModuleConfiguration.AndroidSdkVersions.MIN
import com.j1c1m1b1.chuck.AndroidModuleConfiguration.TEST_INSTRUMENTATION_RUNNER
import com.j1c1m1b1.chuck.AndroidModuleConfiguration.USE_VIEW_BINDING
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
}

android {
    buildFeatures.viewBinding = USE_VIEW_BINDING
    compileSdkVersion(com.j1c1m1b1.chuck.AndroidModuleConfiguration.AndroidSdkVersions.COMPILE)
    defaultConfig {
        applicationId = "com.j1c1m1b1.chuck"
        minSdkVersion(MIN)
        targetSdkVersion(com.j1c1m1b1.chuck.AndroidModuleConfiguration.AndroidSdkVersions.TARGET)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.material)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.fragments)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.junitExt)
    androidTestImplementation(TestLibraries.espresso)
}