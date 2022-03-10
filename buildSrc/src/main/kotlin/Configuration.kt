object Configuration {

    val defaultConfig: DefaultConfig = DefaultConfig()
}

data class DefaultConfig(
    val packageName: String = "com.ciaransloan.pokedexcompose",
    val targetSdk: Int = 31,
    val compileSdk: Int = 31,
    val minSdk: Int = 26,
    val versionCode: Int = 1,
    val versionName: String = "1.0"
)
