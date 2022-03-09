plugins {
    id(BuildPlugins.AndroidLibrary)
    id(BuildPlugins.KotlinAndroid)
    id(BuildPlugins.HiltPlugin)
    kotlin(BuildPlugins.Kapt)
}

android {
    compileSdk = Configuration.defaultConfig.compileSdk

    defaultConfig {
        minSdk = Configuration.defaultConfig.minSdk
        targetSdk = Configuration.defaultConfig.targetSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    api(Dependencies.KotlinExt)
    api(Dependencies.Lifecycle)

    implementation(Dependencies.HiltAndroid)
    kapt(Dependencies.HiltKapt)

    api(Dependencies.Retrofit)
    api(Dependencies.RetrofitMoshiConverter)
    api(Dependencies.Moshi)
    api(Dependencies.OkHttpLogging)
    kapt(Dependencies.MoshiCodeGen)
}
