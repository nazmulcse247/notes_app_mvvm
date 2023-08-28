package com.nazmul.notesapp.ui.base

import android.app.Application
import com.nazmul.notesapp.utils.ConfigureLeakCanary.enableLeakCanary
import com.squareup.leakcanary.core.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            enableLeakCanary(false)
        }
    }

}