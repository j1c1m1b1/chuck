plugins {
    id(BuildPlugins.ANDROID_APP)
    id(BuildPlugins.APP_BASE)
}

android {
    defaultConfig {
        applicationId = "com.j1c1m1b1.chuck"
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
        }
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