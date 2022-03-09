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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.VERSION
    }
}

dependencies {

    implementation(project(":core"))
    api(project(":core-ui-resources"))

    implementation(Dependencies.HiltAndroid)
    kapt(Dependencies.HiltKapt)

    api(Dependencies.Compose.Activity)
    api(Dependencies.Compose.ConstraintLayout)
    api(Dependencies.Compose.Material)
    api(Dependencies.Compose.UI)
    debugApi(Dependencies.Compose.UITooling)

    api(Dependencies.Compose.HiltCompose)

    api(Dependencies.Coil)
}