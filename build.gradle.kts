buildscript {
    dependencies {
        classpath(BuildPlugins.HiltClasspath)
    }
}

plugins {
    id(BuildPlugins.AndroidApplication) version BuildPlugins.Version.ANDROID_APPLICATION apply false
    id(BuildPlugins.AndroidLibrary) version BuildPlugins.Version.ANDROID_LIBRARY apply false
    id(BuildPlugins.KotlinAndroid) version BuildPlugins.Version.KOTLIN_ANDROID apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.Experimental"

            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}
