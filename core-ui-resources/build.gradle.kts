plugins {
    id(BuildPlugins.AndroidLibrary)
}

android {
    compileSdk = Configuration.defaultConfig.compileSdk

    defaultConfig {
        minSdk = Configuration.defaultConfig.minSdk
    }
}
