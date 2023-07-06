package com.dor.mydictionary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyDictionaryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}