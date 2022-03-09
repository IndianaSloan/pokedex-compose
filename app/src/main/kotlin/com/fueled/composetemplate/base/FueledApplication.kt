package com.fueled.composetemplate.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FueledApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
