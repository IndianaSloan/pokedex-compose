object Dependencies {

    private object Version {
        const val LIFECYCLE = "2.4.0"
        const val KOTLIN_EXT = "1.7.0"
        const val RETROFIT = "2.9.0"
        const val COIL = "1.4.0"
        const val MOSHI = "1.13.0"
        const val HILT = "2.38.1"
        const val PALETTE = "1.0.0"
        const val OK_HTTP_LOGGING = "4.2.1"
    }

    const val Coil = "io.coil-kt:coil-compose:${Version.COIL}"
    const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    const val KotlinExt = "androidx.core:core-ktx:${Version.KOTLIN_EXT}"

    const val HiltAndroid = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HiltKapt = "com.google.dagger:hilt-android-compiler:${Version.HILT}"

    const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val OkHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.OK_HTTP_LOGGING}"
    const val RetrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}"
    const val Moshi = "com.squareup.moshi:moshi:${Version.MOSHI}"
    const val MoshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.MOSHI}"

    const val Palette = "androidx.palette:palette-ktx:${Version.PALETTE}"

    object Compose {
        @Suppress("MemberVisibilityCanBePrivate")
        const val VERSION: String = "1.1.0"

        const val Runtime = "androidx.compose.runtime:runtime:${VERSION}"
        const val RuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${VERSION}"
        const val UI = "androidx.compose.ui:ui:${VERSION}"
        const val Material = "androidx.compose.material:material:${VERSION}"
        const val UITooling = "androidx.compose.ui:ui-tooling:${VERSION}"
        const val Foundation = "androidx.compose.foundation:foundation:${VERSION}"
        const val Compiler = "androidx.compose.compiler:compiler:${VERSION}"
        const val MaterialIconsCore = "androidx.compose.material:material-icons-core:${VERSION}"
        const val MaterialIconsExtended =
            "androidx.compose.material:material-icons-extended:${VERSION}"

        private const val constraintLayoutComposeVersion = "1.0.0-rc01"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${constraintLayoutComposeVersion}"

        private const val composeActivitiesVersion = "1.4.0"
        const val Activity = "androidx.activity:activity-compose:${composeActivitiesVersion}"

        private const val composeNavigationVersion = "2.4.0-beta01"
        const val Navigation = "androidx.navigation:navigation-compose:${composeNavigationVersion}"

        private const val hiltVersion = "1.0.0"
        const val HiltCompose = "androidx.hilt:hilt-navigation-compose:${hiltVersion}"
    }
}