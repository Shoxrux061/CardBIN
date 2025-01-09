package com.shoxrux.cardbin.app

import android.app.Application
import com.shoxrux.cardbin.data.room.AppDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataBase.init(this)
    }
}