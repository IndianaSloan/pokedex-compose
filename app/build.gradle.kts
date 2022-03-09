plugins {
    id(BuildPlugins.AndroidApplication)
    id(BuildPlugins.KotlinAndroid)
    id(BuildPlugins.HiltPlugin)
    kotlin(BuildPlugins.Kapt)
}

android {
    compileSdk = Configuration.defaultConfig.compileSdk

    defaultConfig {
        applicationId = Configuration.defaultConfig.packageName
        minSdk = Configuration.defaultConfig.minSdk
        targetSdk = Configuration.defaultConfig.targetSdk
        versionCode = Configuration.defaultConfig.versionCode
        versionName = Configuration.defaultConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":pokemon"))

    implementation(Dependencies.Compose.Navigation)

    implementation(Dependencies.HiltAndroid)
    kapt(Dependencies.HiltKapt)

    kapt(Dependencies.MoshiCodeGen)

}
