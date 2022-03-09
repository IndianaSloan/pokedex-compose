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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.VERSION
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-ui"))

    implementation(Dependencies.Palette)

    implementation(Dependencies.HiltAndroid)
    kapt(Dependencies.HiltKapt)

    kapt(Dependencies.MoshiCodeGen)
}