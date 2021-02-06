package com.j1c1m1b1.chuck

object AndroidModuleConfiguration {
    const val USE_VIEW_BINDING: Boolean = true
    const val TEST_INSTRUMENTATION_RUNNER = "android.support.test.runner.AndroidJUnitRunner"

    object AndroidSdkVersions {
        const val MIN = 21
        const val COMPILE = 30
        const val TARGET = COMPILE
    }

}

