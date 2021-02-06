import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskCollection
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class BaseAndroidConfigurationPlugin : Plugin<Project> {

    private val Project.androidExtension: BaseExtension?
        get() = extensions.getByName(ANDROID_NAME_EXTENSION) as? BaseExtension

    private val Project.kotlinTasks: TaskCollection<KotlinCompile>
        get() = tasks.withType(KotlinCompile::class.java)

    override fun apply(target: Project) {
        target.run {
            plugins.apply(ANDROID_KOTLIN_PLUGIN_ID)
            androidExtension?.configure(target.kotlinTasks)
        }
    }

    private fun BaseExtension.configure(kotlinTasks: TaskCollection<KotlinCompile>) {
        compileSdkVersion(COMPILE_SDK_VERSION)
        configureJava8Compatibility(kotlinTasks)
        setDefaultConfig()
        buildFeatures.viewBinding = true
        configureProguard()
    }

    private fun BaseExtension.configureJava8Compatibility(kotlinTasks: TaskCollection<KotlinCompile>) {
        val javaVersion = JavaVersion.VERSION_1_8
        compileOptions {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        kotlinTasks.configureEach {
            kotlinOptions.jvmTarget = javaVersion.toString()
        }
    }

    private fun BaseExtension.setDefaultConfig() {
        defaultConfig {
            minSdkVersion(MIN_SDK_VERSION)
            targetSdkVersion(COMPILE_SDK_VERSION)
            testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
            versionCode = VERSION
            versionName = VERSION_NAME
        }
    }

    private fun BaseExtension.configureProguard() {
        when (this) {
            is AppExtension -> configureAppProguard()
            is LibraryExtension -> configureLibraryProguard()
        }
    }

    private fun AppExtension.configureAppProguard() {
        buildTypes {
            getByName(RELEASE_BUILD_TYPE) {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile(ANDROID_DEFAULT_PROGUARD_FILE),
                    ANDROID_PROGUARD_RULES
                )
            }
        }
    }

    private fun LibraryExtension.configureLibraryProguard() {
        defaultConfig {
            consumerProguardFiles(CONSUMER_PROGUARD_RULES)
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
        const val CONSUMER_PROGUARD_RULES = "consumer-rules.pro"
        const val ANDROID_DEFAULT_PROGUARD_FILE = "proguard-android-optimize.txt"
        const val ANDROID_PROGUARD_RULES = "proguard-rules.pro"
        const val RELEASE_BUILD_TYPE = "release"
    }
}