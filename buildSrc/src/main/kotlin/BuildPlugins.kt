object BuildPlugins {

    object Version {
        const val ANDROID_APPLICATION = "7.1.2"
        const val ANDROID_LIBRARY = "7.1.2"
        const val KOTLIN_ANDROID = "1.6.10"
        const val HILT = "2.38.1"
    }
    const val AndroidApplication = "com.android.application"
    const val AndroidLibrary = "com.android.library"
    const val KotlinAndroid = "org.jetbrains.kotlin.android"
    const val Kapt = "kapt"
    const val HiltPlugin = "dagger.hilt.android.plugin"

    const val HiltClasspath = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
}